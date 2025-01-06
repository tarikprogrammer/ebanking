package com.botola.apicmi.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String factureCode;
    private String factureName;
    private double price;
    private String clientId;
    private boolean nonPaye;
    private Date dateEcheance;
    private boolean autoPaye ;

}
