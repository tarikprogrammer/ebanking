package com.botola.apiagent.dto;

import com.botola.apiagent.entities.Agent;
import com.botola.apiagent.entities.Otp;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Builder
public record OtpDto(Long id, int otp, LocalDateTime expireDate, Agent agent) {

    public  Otp toEntity(OtpDto otpDto){
        return Otp.builder()
                .id(otpDto.id)
                .otp(otpDto.otp)
                .agent(agent)
                .build();
    }
}
