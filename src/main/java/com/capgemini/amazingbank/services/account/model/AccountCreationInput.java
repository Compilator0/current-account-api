package com.capgemini.amazingbank.services.account.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@Schema
@Data
public class AccountCreationInput {
    @Schema(example = "c24b8345-bed1-4f3a-b4db-577498ef06ce", required = true)
    @NotNull(message = "Customer id must be provided.")
    private String customerID;
    @Schema(example = "8500000", required = true)
    @NotNull(message = "Initial credit must be provided.")
    private Double initialCredit;
}
