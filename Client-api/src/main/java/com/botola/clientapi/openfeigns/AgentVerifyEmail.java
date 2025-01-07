package com.botola.clientapi.openfeigns;


import org.springframework.stereotype.Component;

import org.springframework.web.client.RestTemplate;

@Component
public class AgentVerifyEmail {

    private  RestTemplate restTemplate = new RestTemplate();

    public boolean verifyEmail(String email) {
        String url = "http://3.84.236.79:8081/agent-api/agent/" + email;
        return restTemplate.getForObject(url, Boolean.class);
    }
}
