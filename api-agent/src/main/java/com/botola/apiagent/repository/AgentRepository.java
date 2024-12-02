package com.botola.apiagent.repository;

import com.botola.apiagent.dto.AgentDto;
import com.botola.apiagent.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent,Long> {
    Optional<AgentDto> findByEmailAndPassword(String email, String password);
    Optional<Agent>findByEmail(String email);
}
