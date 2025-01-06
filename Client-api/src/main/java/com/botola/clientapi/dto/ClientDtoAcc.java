package com.botola.clientapi.dto;


import com.botola.clientapi.entities.Client;
import lombok.*;

import java.util.List;



@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDtoAcc {
    private Long id;


    private String fname;


    private String lname;


    private String email;


    private String confirmEmail;


    private String phone;


    private String address;


    private String dateBirth;

    private String IdentityDocument;
    private String NIdentityDocument;
    private String TaxIdentificationNumber;
    private String password;
    private String CommercialRegisterRegistrationNumber;

    private byte[] file;
    private List<AccountDto> accountDtoList;
    private List<TempraryCardDto>tempraryCardDtos;



    public static ClientDtoAcc toDto(Client client) {
        return ClientDtoAcc.builder()
                .id(client.getId())
                .fname(client.getFname())
                .lname(client.getLname())
                .email(client.getEmail())
                .confirmEmail(client.getConfirmEmail())
                .phone(client.getPhone())
                .address(client.getAddress())
                .dateBirth(client.getDateBirth())
                .IdentityDocument(client.getIdentityDocument())
                .NIdentityDocument(client.getNIdentityDocument())
                .TaxIdentificationNumber(client.getTaxIdentificationNumber())
                .CommercialRegisterRegistrationNumber(client.getCommercialRegisterRegistrationNumber())
                .build();
    }

}
