package com.botola.apiagent.services;

import com.botola.apiagent.dto.AgentDto;
import com.botola.apiagent.dto.OtpDto;
import com.botola.apiagent.entities.Agent;
import com.botola.apiagent.entities.Otp;
import com.botola.apiagent.repository.AgentRepository;
import com.botola.apiagent.repository.OtpRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class AgentServicesImp implements AgentService{

    private final AgentRepository agentRepository;
    private final JavaMailSender mailSender;
    private final OtpRepository otpRepository;

    @Override
    public AgentDto login(String email, String password) {
        AgentDto agent = agentRepository.findByEmailAndPassword(email,password).orElseThrow(()->new EntityNotFoundException("ops !! agent not found "));
        return agent;
    }

    @Override
    public void sendOTPtoResetPassword(String email) {
        Agent agent = agentRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException("ops !! agent not found "));
        int otp = generateOtp();
        Otp otpDto = Otp.builder()
                .otp(otp)
                .expireOtp(LocalDateTime.now().plusMinutes(2))
                .agent(agent)
                .build();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Reset Password");
        simpleMailMessage.setFrom("");
        simpleMailMessage.setText("Hi\nTo Reset your Password make sure to copy this otp :"+otp+"\nif you have any problem , Please Contact us as soon as Possible !!");
        mailSender.send(simpleMailMessage);
        otpRepository.save(otpDto);

    }

    @Override
    public boolean verfiyOtp(int otp,String email) {
        Agent agent = agentRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException("ops !! agent not found "));
       int OTP= agent.getOtp().getOtp();
       LocalDateTime now = LocalDateTime.now();
       if(otp == OTP && now.isBefore(agent.getOtp().getExpireOtp())){
           return true;
       }

        return false;
    }

    @Override
    public boolean updatePassword(String newPassword, String email) {
        Agent agent = agentRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException("ops !! agent not found "));
        agent.setPassword(newPassword);
        agentRepository.save(agent);

        return true;
    }

    private int generateOtp(){
        Random random = new Random();
        return random.nextInt(0000,9999);
    }
}
