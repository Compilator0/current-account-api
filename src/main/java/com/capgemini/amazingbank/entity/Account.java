package com.capgemini.amazingbank.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@Entity(name = "accounts")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String accountNumber;

    private double balance;

    private boolean isPrimaryAccount;

    @CreationTimestamp
    private Instant creationDate;

    @UpdateTimestamp
    private Instant lastUpdateDate;

    @ManyToOne
    @JoinColumn(name = "owner_identifier")
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account", orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();
}