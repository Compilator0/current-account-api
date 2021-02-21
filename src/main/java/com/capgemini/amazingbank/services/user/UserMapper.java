package com.capgemini.amazingbank.services.user;

import com.capgemini.amazingbank.entity.Account;
import com.capgemini.amazingbank.entity.User;
import com.capgemini.amazingbank.services.account.AccountMapper;
import com.capgemini.amazingbank.services.user.model.UserDetailsResponse;
import com.capgemini.amazingbank.services.user.model.UserResponse;
import com.capgemini.amazingbank.services.user.model.UserSearchCriteria;
import com.capgemini.amazingbank.services.user.model.UserSearchCriteriaInput;
import com.googlecode.jmapper.JMapper;
import com.googlecode.jmapper.api.JMapperAPI;
import org.springframework.core.convert.converter.Converter;
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
public class UserMapper {
    private AccountMapper accountMapper;

    public UserMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    private JMapperAPI jUserMapperApi = new JMapperAPI()
            .add(mappedClass(UserResponse.class).add(global()))
            .add(mappedClass(UserSearchCriteria.class).add(global()));

    private JMapper<UserResponse, User> userResponseMapper = new JMapper<>(UserResponse.class, User.class, jUserMapperApi);
    private JMapper<UserSearchCriteria, UserSearchCriteriaInput> userSearchCriteriaMapper = new JMapper<>(UserSearchCriteria.class, UserSearchCriteriaInput.class, jUserMapperApi);

    public List<UserResponse> mapUsersToUsersResponse(List<User> users) {
        return users.stream().map(user -> userResponseMapper.getDestination(user)).collect(Collectors.toList());
    }

    public UserSearchCriteria mapUsersSearchCriteriaInputToModel(UserSearchCriteriaInput userSearchCriteriaInput) {
        return userSearchCriteriaMapper.getDestination(userSearchCriteriaInput);
    }

    private Converter<User, UserDetailsResponse> userDetailsResponseConverter = source ->
            UserDetailsResponse.builder()
                    .firstName(source.getFirstName())
                    .surname(source.getSurname())
                    .accounts(accountMapper.convertAccountsToAccountsResponses(source.getAccounts()))
                    .balance(computeUserBalance(source.getAccounts()))
                    .build();


    private Double computeUserBalance(List<Account> accounts) {
        return accounts.stream().mapToDouble(Account::getBalance).sum();
    }

    public Set<UserDetailsResponse> mapUsersToUsersDetailsResponse(List<User> users) {
        return users.stream().map(user -> userDetailsResponseConverter.convert(user)).collect(Collectors.toSet());
    }
}
