package com.botola.clientapi.service;


import com.botola.clientapi.entities.TemporaryCard;
import com.botola.clientapi.openfeigns.AgentVerifyEmail;
import com.botola.clientapi.repositories.ClientRepository;
import com.botola.clientapi.repositories.OtpRepository;
import org.springframework.mail.javamail.JavaMailSender;

public interface TemporaryCardService {

    boolean addTemporaryCard(TemporaryCard temporaryCard,String email);
}
