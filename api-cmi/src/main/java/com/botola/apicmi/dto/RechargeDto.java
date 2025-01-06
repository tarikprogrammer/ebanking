package com.botola.apicmi.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RechargeDto {

    private Long id;

    private String rib;

    private String choixCompte;

    private double montant;

}
