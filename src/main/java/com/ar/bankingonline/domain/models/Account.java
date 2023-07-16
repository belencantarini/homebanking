package com.ar.bankingonline.domain.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

}
