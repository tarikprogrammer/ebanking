package com.botola.agentapi.dto;



import com.botola.agentapi.entities.Agent;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
@Builder

public record AgentDto(Long id, String name, @NotEmpty(message = "email is required") @NotBlank(message = "email is required") @NotNull(message = "email is required") @Email(message = "enter a valid email") String email, @NotEmpty(message = "password is required") @NotNull(message = "password is required") @NotBlank(message = "password is required") String password, String phone){
   public static Agent toEntity(AgentDto agentDto) {
       return Agent.builder()
               .name(agentDto.name)
               .email(agentDto.email)
               .password(agentDto.password)
               .phone(agentDto.phone)
               .build();
   }
}

