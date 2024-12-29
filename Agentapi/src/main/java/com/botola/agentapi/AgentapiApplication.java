package com.botola.agentapi;

import com.botola.agentapi.entities.Agent;
import com.botola.agentapi.repository.AgentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AgentapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgentapiApplication.class, args);
    }

    @Bean
    CommandLineRunner init(AgentRepository agentRepository) {
        return args -> {
            Agent agent =Agent.builder()
                    .name("tarik belaid")
                    .phone("0777871239")
                    .email("belaid.tarikk@gmail.com")
                    .password("agent")
                    .build();

            agentRepository.save(agent);
        };
    }
}
