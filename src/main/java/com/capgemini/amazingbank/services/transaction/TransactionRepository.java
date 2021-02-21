package com.capgemini.amazingbank.services.transaction;

import com.capgemini.amazingbank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
