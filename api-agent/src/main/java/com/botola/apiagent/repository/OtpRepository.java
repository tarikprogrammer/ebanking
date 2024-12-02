package com.botola.apiagent.repository;

import com.botola.apiagent.entities.Otp;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OtpRepository extends JpaRepository<Otp,Long> {
}
