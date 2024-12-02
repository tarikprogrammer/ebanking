package com.botola.apiagent.dto;


import lombok.Builder;

@Builder
public record AgentDto(Long id,String name,String email,String password){

}

