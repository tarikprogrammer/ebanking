package com.botola.apicmi.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TemporaryCrad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String fname;
    private String lname;
    private String email;
    private String choixCompte;
    private double solde;
    private Long clientID;

    private String cvv;

    private String iban;
    private String phone;

    private String expireAt;
    private String device;
}
