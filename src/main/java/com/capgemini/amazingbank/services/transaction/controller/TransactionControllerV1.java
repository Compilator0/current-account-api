package com.capgemini.amazingbank.services.transaction.controller;

import com.capgemini.amazingbank.annotations.DefaultApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@RestController
@DefaultApiResponses
@Tag(name = "Transaction - v1")
@RequestMapping(value = "/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionControllerV1 {
}
