package com.botola.apicmi.entities;

import com.botola.apicmi.utils.GeneratedRef;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    private int plafond ;
    @Column(nullable = false)
    private String iban;
    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @PrePersist
    public void generateReference(){
        this.iban= GeneratedRef.generateRef();
    }

}
