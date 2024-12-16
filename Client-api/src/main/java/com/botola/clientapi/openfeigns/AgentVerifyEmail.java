package com.botola.clientapi.openfeigns;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Agentapi")
public interface AgentVerifyEmail {

    @GetMapping("/agent-api/agent/{email}")
    public boolean verifyEmail(@PathVariable("email") String email);
}
