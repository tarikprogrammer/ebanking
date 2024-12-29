package com.botola.clientapi.dto;

import com.botola.clientapi.entities.Account;
import lombok.Builder;


@Builder
public record AccountDto(Long id , String accountName, double balance, double plafond, String iban) {
    public static Account toEntity(AccountDto accountDto) {
        return Account.builder()
                .accountName(accountDto.accountName)
                .balance(accountDto.balance)
                .plafond(accountDto.plafond)
                .build();

    }
    public static AccountDto toDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .accountName(account.getAccountName())
                .balance(account.getBalance())
                .plafond(account.getPlafond())
                .iban(account.getIban())
                .build();
    }



}
