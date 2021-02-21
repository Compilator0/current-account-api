package com.capgemini.amazingbank.services.transaction;

import com.capgemini.amazingbank.BankInfo;
import com.capgemini.amazingbank.entity.Account;
import com.capgemini.amazingbank.entity.Transaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@ExtendWith(SpringExtension.class)
class TransactionServiceTest {
    @InjectMocks
    private TransactionService transactionService;

    @Spy
    private BankInfo bankInfo;

    @Test
    @DisplayName("Given account and amount, Then build account creation transaction.")
    void buildAccountCreationTransaction() {
        String accountNumber = "a-generated-account-number";
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        Double amount = 255.32D;
        Transaction transaction = transactionService.buildAccountCreationTransaction(account, amount);
        assertNotNull(transaction.getAccount());
        assertEquals(accountNumber, transaction.getAccount().getAccountNumber());
        assertEquals(bankInfo.getBankName(), transaction.getIssuerFullName());
        assertEquals(bankInfo.getBankRef(), transaction.getIssuerRef());
        assertEquals(amount, transaction.getAmount());
        assertNotNull(transaction.getTransactionNumber());
        assertEquals("ONBOARDING", transaction.getReason());
    }
}