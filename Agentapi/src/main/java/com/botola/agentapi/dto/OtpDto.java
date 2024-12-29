package com.botola.agentapi.dto;


import com.botola.agentapi.entities.Agent;
import com.botola.agentapi.entities.Otp;
import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public record OtpDto(Long id, int otp, LocalDateTime expireDate, Agent agent) {

    public Otp toEntity(OtpDto otpDto){
        return Otp.builder()
                .id(otpDto.id)
                .otp(otpDto.otp)
                .agent(agent)
                .build();
    }
}
