package com.capgemini.amazingbank.services.account;

import com.capgemini.amazingbank.entity.Account;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.capgemini.amazingbank.services.account.AccountTestsHelper.generateFakeAccounts;
import static org.mockito.Mockito.when;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@ExtendWith(SpringExtension.class)
class AccountServiceTest {
    @InjectMocks
    private AccountService accountService;
    @Mock
    private AccountRepository accountRepository;

    @Test
    @DisplayName("Test get all accounts method.")
    void getAccounts() {
        when(accountRepository.findAll()).thenReturn(generateFakeAccounts(4));
        List<Account> accounts = accountService.getAccounts();
        Assertions.assertThat(accounts).isNotEmpty();
    }
}