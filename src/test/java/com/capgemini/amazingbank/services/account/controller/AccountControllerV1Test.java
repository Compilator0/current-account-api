package com.capgemini.amazingbank.services.account.controller;

import com.capgemini.amazingbank.AmazingbankApplication;
import com.capgemini.amazingbank.common.TestsSharedHandler;
import com.capgemini.amazingbank.services.account.model.AccountCreationInput;
import com.capgemini.amazingbank.services.account.model.AccountResponse;
import com.capgemini.amazingbank.services.transaction.model.TransactionResponse;
import com.capgemini.amazingbank.services.user.model.UserDetailsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

import static com.capgemini.amazingbank.services.user.controller.UserControllerV1Test.USERS_DETAILS_URI;
import static com.capgemini.amazingbank.services.user.controller.UserControllerV1Test.checkCommonAssertionsForTransactionSample;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@SpringBootTest(classes = AmazingbankApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test"})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:test-data-sample_up.sql"})
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {"classpath:test-data-sample_down.sql"})
class AccountControllerV1Test {
    private String ACCOUNTS_BASE_URL;
    private String SEARCH_USERS_DETAILS_BASE_URL;

    @Autowired
    AccountControllerV1Test(TestsSharedHandler testsSharedHandler) {
        this.ACCOUNTS_BASE_URL = testsSharedHandler.getApiUrlPrefix() + ACCOUNTS_URI;
        this.SEARCH_USERS_DETAILS_BASE_URL = testsSharedHandler.getApiUrlPrefix() + USERS_DETAILS_URI;
    }

    private final static String ACCOUNTS_URI = "/v1/accounts";

    @Test
    @DisplayName("Endpoint " + ACCOUNTS_URI + " : Test the retrieval of all accounts.")
    void testGetAccounts() {
        ResponseEntity<Set<UserDetailsResponse>> response = new RestTemplate().exchange(ACCOUNTS_BASE_URL, HttpMethod.GET,
                null, new ParameterizedTypeReference<Set<UserDetailsResponse>>() {
                });
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Set<UserDetailsResponse> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(4, responseBody.size());
    }

    @Test
    @DisplayName("Endpoint " + ACCOUNTS_URI + " : Test accounts creation scenarios.")
    void testAccountCreationScenarios() {
        String targetCustomerID = "c24b8345-bed1-4f3a-b4db-577498ef06ce";
        String searchUrl = SEARCH_USERS_DETAILS_BASE_URL + "?identifier=" + targetCustomerID;

        // Check customer number of accounts before new account creation
        UserDetailsResponse userDetails = searchCustomerDetailsGivenID(searchUrl);
        int customerInitialNberOfAccounts = userDetails.getAccounts().size();

        // We create a new current account with non null initial credit for the customer
        Double newAccountInitialCredit = 256.56;
        testNewAccountCreationWithNonNullInitialCredit(targetCustomerID, newAccountInitialCredit);

        // We create a new current account with a null initial credit for the customer
        testNewAccountCreationWithNullInitialCredit(targetCustomerID);

        // We check that the customer has two new accounts
        userDetails = searchCustomerDetailsGivenID(searchUrl);
        assertEquals(customerInitialNberOfAccounts + 2, userDetails.getAccounts().size());

        // check account creation with invalid customer ID throws exception
        testNewAccountCreationThrowsUnknownCustomerException();
    }

    private void testNewAccountCreationThrowsUnknownCustomerException() {
        AccountCreationInput accountCreationInput = new AccountCreationInput();
        accountCreationInput.setCustomerID("an-unknown-customer-id");
        accountCreationInput.setInitialCredit(0D);
        HttpClientErrorException thrown = assertThrows(HttpClientErrorException.class, () -> new RestTemplate().exchange(ACCOUNTS_BASE_URL, HttpMethod.POST,
                new HttpEntity<>(accountCreationInput), new ParameterizedTypeReference<AccountResponse>() {
                }));
        assertEquals(HttpStatus.BAD_REQUEST.value(), thrown.getRawStatusCode());
    }

    private UserDetailsResponse searchCustomerDetailsGivenID(String searchUrl) {
        ResponseEntity<Set<UserDetailsResponse>> response = new RestTemplate().exchange(searchUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<Set<UserDetailsResponse>>() {
                });
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Set<UserDetailsResponse> responseBody = response.getBody();
        assertThat(responseBody).isNotNull().isNotEmpty();
        return responseBody.iterator().next();
    }

    private void testNewAccountCreationWithNonNullInitialCredit(String customerID, Double initialCredit) {
        AccountResponse accountCreationResponseBody = testAccountCreationCommonWithCommonChecks(customerID, initialCredit);
        assertThat(accountCreationResponseBody.getTransactions()).isNotNull().hasSize(1);
        TransactionResponse transactionSample = accountCreationResponseBody.getTransactions().iterator().next();
        assertNotNull(transactionSample);
        checkCommonAssertionsForTransactionSample(transactionSample);
        assertEquals(initialCredit, transactionSample.getAmount());
    }

    private void testNewAccountCreationWithNullInitialCredit(String customerID) {
        AccountResponse accountCreationResponseBody = testAccountCreationCommonWithCommonChecks(customerID, 0D);
        assertThat(accountCreationResponseBody.getTransactions()).isNotNull().hasSize(0);
    }

    private AccountResponse testAccountCreationCommonWithCommonChecks(String customerID, Double initialCredit) {
        AccountCreationInput accountCreationInput = new AccountCreationInput();
        accountCreationInput.setCustomerID(customerID);
        accountCreationInput.setInitialCredit(initialCredit);
        ResponseEntity<AccountResponse> accountCreationResponse = new RestTemplate().exchange(ACCOUNTS_BASE_URL, HttpMethod.POST,
                new HttpEntity<>(accountCreationInput), new ParameterizedTypeReference<AccountResponse>() {
                });
        assertEquals(HttpStatus.CREATED, accountCreationResponse.getStatusCode());
        AccountResponse accountCreationResponseBody = accountCreationResponse.getBody();
        assertNotNull(accountCreationResponseBody);
        checkAccountCreationCommonAssertions(accountCreationResponseBody);
        return accountCreationResponseBody;
    }

    private void checkAccountCreationCommonAssertions(AccountResponse accountCreationResponseBody) {
        assertNotNull(accountCreationResponseBody.getBalance());
        assertNotNull(accountCreationResponseBody.getAccountNumber());
    }
}