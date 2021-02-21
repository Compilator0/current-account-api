package com.capgemini.amazingbank;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@Component
@PropertySource("classpath:cab.yml")
@Getter
public class BankInfo {
    @Value("${bank.bankName}")
    private String bankName;

    @Value("${bank.bankRef}")
    private String bankRef;
}
