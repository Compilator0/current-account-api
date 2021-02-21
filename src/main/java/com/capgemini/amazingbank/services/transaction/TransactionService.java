package com.capgemini.amazingbank.services.transaction;

import com.capgemini.amazingbank.BankInfo;
import com.capgemini.amazingbank.entity.Account;
import com.capgemini.amazingbank.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@Service
@RequiredArgsConstructor
public class TransactionService {
    private final BankInfo bankInfo;
    private static final String ONBOARDING = "ONBOARDING";

    /**
     * Build account creation transaction
     *
     * @param account an account entity
     * @param amount  the amount for the transaction build
     * @return a freshly built account creation transaction
     */
    public Transaction buildAccountCreationTransaction(Account account, Double amount) {
        Transaction transaction = buildNewTransaction(bankInfo.getBankRef(), bankInfo.getBankName(), amount, ONBOARDING);
        transaction.setAccount(account);
        return transaction;
    }

    /**
     * @param issuerRef      issuer reference parameter
     * @param issuerFullName issuer full name parameter
     * @param amount         amount for transaction build
     * @param reason         reason of the transaction
     * @return a freshly built transaction
     */
    private Transaction buildNewTransaction(String issuerRef, String issuerFullName, Double amount, String reason) {
        Transaction transaction = new Transaction();
        transaction.setIssuerRef(issuerRef);
        transaction.setIssuerFullName(issuerFullName);
        transaction.setAmount(amount);
        transaction.setReason(reason);
        transaction.setTransactionNumber(UUID.randomUUID().toString());
        return transaction;
    }
}
