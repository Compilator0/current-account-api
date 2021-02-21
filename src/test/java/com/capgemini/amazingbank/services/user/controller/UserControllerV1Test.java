package com.capgemini.amazingbank.services.user.controller;

import com.capgemini.amazingbank.AmazingbankApplication;
import com.capgemini.amazingbank.common.TestsSharedHandler;
import com.capgemini.amazingbank.services.account.model.AccountResponse;
import com.capgemini.amazingbank.services.transaction.model.TransactionResponse;
import com.capgemini.amazingbank.services.user.model.UserDetailsResponse;
import com.capgemini.amazingbank.services.user.model.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@SpringBootTest(classes = AmazingbankApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test"})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:test-data-sample_up.sql"})
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {"classpath:test-data-sample_down.sql"})
public class UserControllerV1Test {
    private String SEARCH_USERS_DETAILS_BASE_URL;
    private String GET_USERS_BASE_URL;

    private final static String USERS_URI = "/v1/users";
    public final static String USERS_DETAILS_URI = "/v1/users-details";

    @Autowired
    UserControllerV1Test(TestsSharedHandler testsSharedHandler) {
        this.GET_USERS_BASE_URL = testsSharedHandler.getApiUrlPrefix() + USERS_URI;
        this.SEARCH_USERS_DETAILS_BASE_URL = testsSharedHandler.getApiUrlPrefix() + USERS_DETAILS_URI;
    }

    @Test
    @DisplayName("Endpoint " + USERS_URI + " : Test the retrieval of all users.")
    void getUsersTest() {
        ResponseEntity<List<UserResponse>> response = new RestTemplate().exchange(GET_USERS_BASE_URL, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<UserResponse>>() {
                });
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<UserResponse> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(3, responseBody.size());
    }

    @Test
    @DisplayName("Endpoint " + USERS_DETAILS_URI + " : Test the search of users details.")
    void searchUsersTest() {
        // Search with no criteria
        ResponseEntity<Set<UserDetailsResponse>> response = new RestTemplate().exchange(SEARCH_USERS_DETAILS_BASE_URL, HttpMethod.GET,
                null, new ParameterizedTypeReference<Set<UserDetailsResponse>>() {
                });
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Set<UserDetailsResponse> responseBody = response.getBody();
        assertThat(responseBody).isNotNull().hasSize(3);
        checkCommonAssertionsForUserDetailsSample(responseBody.iterator().next());

        // Search given a single criteria
        String searchUrl = SEARCH_USERS_DETAILS_BASE_URL + "?identifier=c24b8345-bed1-4f3a-b4db-577498ef06ce";
        response = new RestTemplate().exchange(searchUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<Set<UserDetailsResponse>>() {
                });
        assertEquals(HttpStatus.OK, response.getStatusCode());
        responseBody = response.getBody();
        assertThat(responseBody).isNotNull().hasSize(1);
        UserDetailsResponse userDetailsSample = responseBody.iterator().next();
        checkCommonAssertionsForUserDetailsSample(userDetailsSample);
        assertEquals("Aliko", userDetailsSample.getFirstName());
        assertEquals("DANGOTE", userDetailsSample.getSurname());

        // Search given two criteria
        searchUrl = SEARCH_USERS_DETAILS_BASE_URL + "?identifier=cf8d3518-d503-449a-9093-3a4819b01589&email=ibrasherr@cab.com";
        response = new RestTemplate().exchange(searchUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<Set<UserDetailsResponse>>() {
                });
        assertEquals(HttpStatus.OK, response.getStatusCode());
        responseBody = response.getBody();
        assertThat(responseBody).isNotNull().hasSize(1);
        userDetailsSample = responseBody.iterator().next();
        checkCommonAssertionsForUserDetailsSample(userDetailsSample);
        assertEquals("Ibrahim", userDetailsSample.getFirstName());
        assertEquals("SHERIFF", userDetailsSample.getSurname());

        // Search given all criteria
        searchUrl = SEARCH_USERS_DETAILS_BASE_URL + "?identifier=cf23ff12-e26b-4e5a-b71a-ebc80d8da0b5&email=idriscompil0@cab.com&firstName=Idris&surname=COMPILATOR";
        response = new RestTemplate().exchange(searchUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<Set<UserDetailsResponse>>() {
                });
        assertEquals(HttpStatus.OK, response.getStatusCode());
        responseBody = response.getBody();
        assertThat(responseBody).isNotNull().hasSize(1);
        userDetailsSample = responseBody.iterator().next();
        checkCommonAssertionsForUserDetailsSample(userDetailsSample);
        assertEquals("Idris", userDetailsSample.getFirstName());
        assertEquals("COMPILATOR", userDetailsSample.getSurname());
    }

    private void checkCommonAssertionsForUserDetailsSample(UserDetailsResponse userDetailsSample) {
        assertNotNull(userDetailsSample.getFirstName());
        assertNotNull(userDetailsSample.getSurname());
        assertNotNull(userDetailsSample.getBalance());
        assertNotNull(userDetailsSample.getAccounts());
        if (isNotEmpty(userDetailsSample.getAccounts())) {
            AccountResponse userAccountSample = userDetailsSample.getAccounts().iterator().next();
            assertNotNull(userAccountSample.getAccountNumber());
            assertNotNull(userAccountSample.getBalance());
            if (isNotEmpty(userAccountSample.getTransactions())) {
                TransactionResponse transactionResponseSample = userAccountSample.getTransactions().iterator().next();
                checkCommonAssertionsForTransactionSample(transactionResponseSample);
            }
        }
    }

    public static void checkCommonAssertionsForTransactionSample(TransactionResponse transactionResponseSample) {
        assertNotNull(transactionResponseSample.getInitiationDate());
        assertNotNull(transactionResponseSample.getIssuerRef());
        assertNotNull(transactionResponseSample.getIssuerFullName());
        assertNotNull(transactionResponseSample.getTransactionNumber());
        assertNotNull(transactionResponseSample.getAmount());
        assertNotNull(transactionResponseSample.getReason());
    }

}