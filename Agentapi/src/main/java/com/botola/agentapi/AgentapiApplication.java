package com.botola.agentapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AgentapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgentapiApplication.class, args);
    }
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
