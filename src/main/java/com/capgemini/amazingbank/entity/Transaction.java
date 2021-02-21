package com.capgemini.amazingbank.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@Entity(name = "transactions")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String transactionNumber;

    private double amount;

    private String issuerRef;

    private String issuerFullName;

    private String reason;

    @CreationTimestamp
    private Instant initiationDate;

    @UpdateTimestamp
    private Instant lastUpdateDate;

    private Instant cancellationDate;

    @ManyToOne
    private Account account;
}
