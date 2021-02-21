package com.capgemini.amazingbank.services.transaction.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@Schema(description = "Transaction")
@Data
@NoArgsConstructor
public class TransactionResponse {
    @Schema(example = "e5a39bee-e38b-45d1")
    private String transactionNumber;
    @Schema(example = "80")
    private Double amount;
    @Schema(example = "CAB-LYON-R56TREO")
    private String issuerRef;
    @Schema(example = "CAPGEMINI AMAZING BANK LYON")
    private String issuerFullName;
    @Schema(example = "ONBOARDING OFFER")
    private String reason;
    @Schema(example = "2021-02-19 21:22:53.191207+01")
    private Instant initiationDate;
}