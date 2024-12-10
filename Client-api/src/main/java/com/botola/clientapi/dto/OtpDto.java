package com.botola.clientapi.dto;


import com.botola.clientapi.entities.Client;
import com.botola.clientapi.entities.Otp;
import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public record OtpDto(Long id, int otp, LocalDateTime expireDate, Client agent) {

    public Otp toEntity(OtpDto otpDto){
        return Otp.builder()
                .id(otpDto.id)
                .otp(otpDto.otp)
                .agent(agent)
                .build();
    }
}
