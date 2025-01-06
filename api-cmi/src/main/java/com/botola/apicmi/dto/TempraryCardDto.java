package com.botola.apicmi.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TempraryCardDto {

    private String name;
    private String fname;
    private String lname;
    private String email;
    private String choixCompte;
    private double solde;
    private String expireAt;
    private String iban;
    private String phone;
}
