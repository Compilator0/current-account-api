package com.capgemini.amazingbank.services.account;

import com.capgemini.amazingbank.entity.Account;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
public class AccountTestsHelper {
    public static List<Account> generateFakeAccounts(int nbAccounts) {
        if (nbAccounts < 1) {
            return Collections.emptyList();
        }
        String accountNumber = "h4556s6-c00d-4b24-8aa9-66cd7a6a9581";
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < nbAccounts; i++) {
            Account account = new Account();
            account.setId(i + 1);
            account.setAccountNumber(i + accountNumber);
            account.setBalance((i + 1) * 3);
            accounts.add(account);
        }
        return accounts;
    }
}
