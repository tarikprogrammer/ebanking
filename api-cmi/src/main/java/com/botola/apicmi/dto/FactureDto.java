package com.botola.apicmi.dto;


import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class FactureDto {
    private Long id;

    private String factureCode;
    private String factureName;
    private double price;
    private boolean nonPaye;
    private Date dateEcheance;
    private boolean autoPaye ;
}
