package com.botola.clientapi.dto;

import lombok.*;

import java.util.Date;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class TempraryCardDto {

    private String name;
    private String fname;
    private String lname;
    private String email;
    private String choixCompte;
    private double solde;
    private String expireAt;
    private String iban;
}

