package com.botola.apicmi.entities;


import jakarta.persistence.*;
import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double solde;

    private String accountName;
    private double plafond ;
    @Column(nullable = false)
    private String iban;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    private String cvv;

    private String expireAt;
    private String device;

}
