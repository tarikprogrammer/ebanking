package com.botola.agentapi.repository;


import com.botola.agentapi.dto.AgentDto;
import com.botola.agentapi.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent,Long> {
    Optional<AgentDto> findByEmailAndPassword(String email, String password);
    Optional<Agent>findByEmail(String email);
}
