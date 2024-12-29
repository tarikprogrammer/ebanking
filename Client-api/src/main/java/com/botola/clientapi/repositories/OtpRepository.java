package com.botola.clientapi.repositories;


import com.botola.clientapi.entities.Otp;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OtpRepository extends JpaRepository<Otp,Long> {
}
