package com.capgemini.amazingbank.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@Entity(name = "users")
@Data
public class User {
    @Id
    private String identifier;

    @Email
    @Column(unique = true)
    private String email;

    private String firstName;

    private String surname;

    @CreationTimestamp
    private Instant creationDate;

    @UpdateTimestamp
    private Instant lastUpdateDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", orphanRemoval = true)
    private List<Account> accounts = new ArrayList<>();
}
