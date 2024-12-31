package com.botola.clientapi.dto;

import com.botola.clientapi.entities.TemporaryCard;
import lombok.Builder;

import java.util.Date;
@Builder
public record TemporaryCardDto(Long id, String cardNumber, Date expiredAt,String cvv,double amount) {

    public static TemporaryCardDto temporaryCardDto(TemporaryCard temporaryCard) {
        return TemporaryCardDto.builder()
                .id(temporaryCard.getId())
                .cardNumber(temporaryCard.getCardNumber())
                .expiredAt(temporaryCard.getExpiredAt())
                .cvv(temporaryCard.getCvv())
                .amount(temporaryCard.getAmount())
                .build();
    }


    public static TemporaryCard toEntity(TemporaryCardDto temporaryCardDto) {
        return TemporaryCard.builder()
                .cardNumber(temporaryCardDto.cardNumber())
                .expiredAt(temporaryCardDto.expiredAt())
                .cvv(temporaryCardDto.cvv())
                .amount(temporaryCardDto.amount())
                .build();
    }
}
