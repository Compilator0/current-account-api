package com.capgemini.amazingbank.services.user.controller;

import com.capgemini.amazingbank.annotations.DefaultApiResponses;
import com.capgemini.amazingbank.services.user.UserMapper;
import com.capgemini.amazingbank.services.user.UserService;
import com.capgemini.amazingbank.services.user.model.UserDetailsResponse;
import com.capgemini.amazingbank.services.user.model.UserResponse;
import com.capgemini.amazingbank.services.user.model.UserSearchCriteriaInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@RestController
@DefaultApiResponses
@Tag(name = "User - v1")
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class UserControllerV1 {
    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Get all users.", description = "Get all the users.")
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserResponse>> getUsers() {
        return new ResponseEntity<>(userMapper.mapUsersToUsersResponse(userService.getUsers()), HttpStatus.OK);
    }

    @Operation(summary = "Search users with account details.", description = "Use this endpoint to search the users with their associated accounts.")
    @GetMapping(value = "/users-details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<UserDetailsResponse>> searchUsers(@Valid @ModelAttribute UserSearchCriteriaInput userSearchCriteriaInput) {
        return new ResponseEntity<>(userMapper.mapUsersToUsersDetailsResponse(userService.searchUsers(userMapper.mapUsersSearchCriteriaInputToModel(userSearchCriteriaInput))), HttpStatus.OK);
    }
}
