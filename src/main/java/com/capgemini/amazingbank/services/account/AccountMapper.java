package com.capgemini.amazingbank.services.account;

import com.capgemini.amazingbank.entity.Account;
import com.capgemini.amazingbank.services.account.model.AccountCreationInput;
import com.capgemini.amazingbank.services.account.model.AccountCreationModel;
import com.capgemini.amazingbank.services.account.model.AccountEnrichedResponse;
import com.capgemini.amazingbank.services.account.model.AccountResponse;
import com.capgemini.amazingbank.services.transaction.TransactionMapper;
import com.googlecode.jmapper.JMapper;
import com.googlecode.jmapper.api.JMapperAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.googlecode.jmapper.api.JMapperAPI.global;
import static com.googlecode.jmapper.api.JMapperAPI.mappedClass;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@Component
@RequiredArgsConstructor
public class AccountMapper {
    private final TransactionMapper transactionMapper;

    private JMapperAPI jAccountMapperApi = new JMapperAPI()
            .add(mappedClass(AccountCreationModel.class).add(global()));

    private JMapper<AccountCreationModel, AccountCreationInput> accountCreationModelMapper = new JMapper<>(AccountCreationModel.class, AccountCreationInput.class, jAccountMapperApi);

    public AccountCreationModel mapAccountCreationInputToModel(AccountCreationInput accountCreationInput) {
        return accountCreationModelMapper.getDestination(accountCreationInput);
    }

    public AccountResponse convertAccountToAccountResponse(Account source) {
        return AccountResponse.builder().accountNumber(source.getAccountNumber())
                .balance(source.getBalance())
                .transactions(transactionMapper.mapTransactionsToTransactionsResponse(source.getTransactions()))
                .build();
    }

    public Set<AccountResponse> convertAccountsToAccountsResponses(List<Account> accounts) {
        return accounts.stream().map(this::convertAccountToAccountResponse).collect(Collectors.toSet());
    }

    private AccountEnrichedResponse convertAccountToAccountEnrichedResponse(Account source) {
        AccountEnrichedResponse accountEnrichedResponse = AccountEnrichedResponse.builder()
                .accountNumber(source.getAccountNumber())
                .balance(source.getBalance())
                .transactions(transactionMapper.mapTransactionsToTransactionsResponse(source.getTransactions()))
                .build();
        accountEnrichedResponse.setFirstName(source.getOwner().getFirstName());
        accountEnrichedResponse.setSurname(source.getOwner().getSurname());
        return accountEnrichedResponse;
    }

    public Set<AccountEnrichedResponse> convertAccountsToAccountsEnrichedResponses(List<Account> accounts) {
        return accounts.stream().map(this::convertAccountToAccountEnrichedResponse).collect(Collectors.toSet());
    }
}
