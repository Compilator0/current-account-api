package com.capgemini.amazingbank.services.account;

import com.capgemini.amazingbank.entity.Account;
import com.capgemini.amazingbank.entity.User;
import com.capgemini.amazingbank.services.account.model.AccountCreationModel;
import com.capgemini.amazingbank.services.transaction.TransactionService;
import com.capgemini.amazingbank.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {
    private final UserService userService;
    private final TransactionService transactionService;
    private final AccountRepository accountRepository;

    /**
     * Get the list of all the existing accounts
     * @return The existing accounts.
     */
    public List<Account> getAccounts() {
        log.info("Requesting all the existing accounts.");
        return accountRepository.findAll();
    }

    /**
     * Create a new current account given creation data
     * @param accountCreationData The necessary data for the account creation
     * @return The created account
     */
    public Account createAccount(AccountCreationModel accountCreationData) {
        log.info("Creation of a new current account");
        User user = userService.getUser(accountCreationData.getCustomerID());
        Account account = new Account();
        account.setAccountNumber(UUID.randomUUID().toString());
        account.setPrimaryAccount(true);
        if (accountCreationData.getInitialCredit() != 0) {
            account.setTransactions(Collections.singletonList(transactionService.buildAccountCreationTransaction(account, accountCreationData.getInitialCredit())));
            account.setBalance(accountCreationData.getInitialCredit());
        }
        account.setOwner(user);
        return accountRepository.save(account);
    }
}
