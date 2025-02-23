package com.example.flyway.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@Table(name = "MASTER_ACCOUNT", schema = "BACKOFFICE_XE")
public class MasterAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private MasterUser user;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;
}
