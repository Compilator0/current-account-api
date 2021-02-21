package com.capgemini.amazingbank.services.account.model;

import lombok.Data;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@Data
public class AccountCreationModel {
    private String customerID;
    private Double initialCredit;
}
