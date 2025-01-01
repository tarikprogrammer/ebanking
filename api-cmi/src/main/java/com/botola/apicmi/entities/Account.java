package com.botola.apicmi.entities;

import com.botola.apicmi.utils.GeneratedRef;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountName;
    private double plafond ;
    private double solde;

    @Column(nullable = false)
    private String iban;
    @Column(name = "client_id", nullable = false)
    private Long clientId;

    private String cvv;

    private String expireAt;
    private String device;

}
