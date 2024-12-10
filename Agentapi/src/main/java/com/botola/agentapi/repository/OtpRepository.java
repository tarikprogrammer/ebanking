package com.botola.agentapi.repository;

import com.botola.agentapi.entities.Otp;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OtpRepository extends JpaRepository<Otp,Long> {
}
