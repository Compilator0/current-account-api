package com.capgemini.amazingbank.services.account;

import com.capgemini.amazingbank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
