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
    private int plafond;
    private double solde;
    private String iban;
    private Long clientId;


    // Convert Account entity to AccountDto
    public static AccountDto toDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .plafond(account.getPlafond())
                .solde(account.getSolde())
                .iban(account.getIban())
                .clientId(account.getClientId())
                .build();
    }

    // Convert AccountDto to Account entity
    public static Account toAccount(AccountDto accountDto) {
        return Account.builder()
                .id(accountDto.getId())
                .plafond(accountDto.getPlafond())
                .solde(accountDto.getSolde())
                .iban(accountDto.getIban())
                .clientId(accountDto.getClientId())
                .build();
    }
}

