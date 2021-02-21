package com.capgemini.amazingbank.services.account.model;

import com.capgemini.amazingbank.services.transaction.model.TransactionResponse;
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
@Schema(description = "Account")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    @Schema(example = "8b45d3cd-4f8a-4b1a-bac6-9b57693203f2")
    private String accountNumber;
    @Schema(example = "200")
    private Double balance;
    @Schema
    private Set<TransactionResponse> transactions;
}