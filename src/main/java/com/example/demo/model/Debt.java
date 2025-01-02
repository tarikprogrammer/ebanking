package com.example.demo.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "debts")
@Getter
@Setter
public class Debt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private String debtorName;

    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "creditor_id")
    @JsonBackReference
    private Creditor creditor;

    @Transient
    private Long creditorId;
}
