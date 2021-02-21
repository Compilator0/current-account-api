package com.capgemini.amazingbank.services.user.model;

import com.capgemini.amazingbank.services.account.model.AccountResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@Schema(description = "UserDetails")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsResponse {
    @Schema(example = "Idris")
    private String firstName;
    @Schema(example = "COMPILATOR")
    private String surname;
    @Schema(example = "200")
    private Double balance;
    @Schema
    private Set<AccountResponse> accounts;
}