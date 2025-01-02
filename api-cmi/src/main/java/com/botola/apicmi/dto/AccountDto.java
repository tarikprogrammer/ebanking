package com.botola.apicmi.dto;

import com.botola.apicmi.entities.Account;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Data
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


    // Convert Account entity to AccountDto
    public static AccountDto toDto(Account account) {
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

    // Convert AccountDto to Account entity
    public static Account toAccount(AccountDto accountDto) {
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
}

