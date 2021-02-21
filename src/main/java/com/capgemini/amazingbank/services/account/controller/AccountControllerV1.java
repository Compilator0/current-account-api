package com.capgemini.amazingbank.services.account.controller;

import com.capgemini.amazingbank.annotations.DefaultApiResponses;
import com.capgemini.amazingbank.services.account.AccountMapper;
import com.capgemini.amazingbank.services.account.AccountService;
import com.capgemini.amazingbank.services.account.model.AccountCreationInput;
import com.capgemini.amazingbank.services.account.model.AccountEnrichedResponse;
import com.capgemini.amazingbank.services.account.model.AccountResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@RestController
@DefaultApiResponses
@Tag(name = "Account - v1")
@RequestMapping(value = "/api/v1/accounts")
@RequiredArgsConstructor
public class AccountControllerV1 {
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @Operation(summary = "Get all accounts.", description = "Get all the existing accounts with transactions details.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<AccountEnrichedResponse>> getAccounts() {
        return new ResponseEntity<>(accountMapper.convertAccountsToAccountsEnrichedResponses(accountService.getAccounts()), HttpStatus.OK);
    }

    @Operation(summary = "Create a new current account for a user.", description = "Use this endpoint to create a new current account for a user.")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountCreationInput accountCreationInput) {
        return new ResponseEntity<>(accountMapper.convertAccountToAccountResponse(accountService.createAccount(accountMapper.mapAccountCreationInputToModel(accountCreationInput))), HttpStatus.CREATED);
    }
}
