package com.botola.apicmi.dto;


import com.botola.apicmi.entities.Account;
import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder


public class AccountDto {

    private Long id;

    private double solde;

    private double plafond ;

    private String iban;

    private Long clientId;

    private String cvv;

    private String expireAt;

    private String device;
    private String accountName;


    public static Account toEntity(AccountDto accountDto){
        return Account.builder()
                .solde(accountDto.getSolde())
                .plafond(accountDto.getPlafond())
                .iban(accountDto.getIban())
                .cvv(accountDto.getCvv())
                .expireAt(accountDto.getExpireAt())
                .device(accountDto.getDevice())
                .accountName(accountDto.getAccountName())
                .build();
    }


    public static AccountDto toDto(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .solde(account.getSolde())
                .plafond(account.getPlafond())
                .iban(account.getIban())
                .cvv(account.getCvv())
                .expireAt(account.getExpireAt())
                .device(account.getDevice())
                .accountName(account.getAccountName())
                .build();
    }


}
