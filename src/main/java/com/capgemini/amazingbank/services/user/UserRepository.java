package com.capgemini.amazingbank.services.user;

import com.capgemini.amazingbank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
}
