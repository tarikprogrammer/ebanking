package com.botola.clientapi.dto;


import lombok.Builder;


@Builder
public record AccountDto(Long id , String accountName, double solde, double plafond, String iban,String expireAt,String device) {

}
