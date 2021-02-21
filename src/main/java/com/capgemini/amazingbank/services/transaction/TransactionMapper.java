package com.capgemini.amazingbank.services.transaction;

import com.capgemini.amazingbank.entity.Transaction;
import com.capgemini.amazingbank.services.transaction.model.TransactionResponse;
import com.googlecode.jmapper.JMapper;
import com.googlecode.jmapper.api.JMapperAPI;
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
public class TransactionMapper {
    private JMapperAPI jTransactionMapperApi = new JMapperAPI()
            .add(mappedClass(TransactionResponse.class).add(global()));

    private JMapper<TransactionResponse, Transaction> transactionResponseMapper = new JMapper<>(TransactionResponse.class, Transaction.class, jTransactionMapperApi);

    public Set<TransactionResponse> mapTransactionsToTransactionsResponse(List<Transaction> transactions) {
        return transactions.stream().map(user -> transactionResponseMapper.getDestination(user)).collect(Collectors.toSet());
    }
}
