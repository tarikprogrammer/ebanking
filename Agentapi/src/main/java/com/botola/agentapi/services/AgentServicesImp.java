package com.botola.agentapi.services;


import com.botola.agentapi.dto.AgentDto;
import com.botola.agentapi.entities.Agent;
import com.botola.agentapi.entities.Otp;
import com.botola.agentapi.repository.AgentRepository;
import com.botola.agentapi.repository.OtpRepository;
import com.botola.agentapi.validitions.ObjectValidator;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class AgentServicesImp implements AgentService{

    private final AgentRepository agentRepository;
    private final JavaMailSender mailSender;
    private final OtpRepository otpRepository;
    private final ObjectValidator<AgentDto> objectValidator;

    @Override
    public AgentDto login(String email, String password) {
        objectValidator.validate(AgentDto.builder().email(email).password(password).build());
        AgentDto agent = agentRepository.findByEmailAndPassword(email,password).orElseThrow(()->new EntityNotFoundException("Email or Password Not Found"));
        return agent;
    }

    @Override
    public void sendOTPtoResetPassword(String email) {
        Agent agent = agentRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException("Email not found"));
        Path path = Paths.get("/Users/tarik/Desktop/ebanking/Agentapi/src/main/resources/templates/sendLinkPassword.html");
        try {
            String htmlContent = new String(Files.readAllBytes(path));
            int otp = generateOtp();
            String updatedHtmlContent = htmlContent.replace("{{otp}}", String.valueOf(otp));
            if(agent.getOtp() ==null){
                Otp otpDto = Otp.builder()
                        .otp(otp)
                        .expireOtp(LocalDateTime.now().plusMinutes(2))
                        .agent(agent)
                        .build();
                otpRepository.save(otpDto);
            }else{
                Otp otp1 =agent.getOtp();
                otp1.setOtp(otp);
                otp1.setExpireOtp(LocalDateTime.now().plusMinutes(2));
                otpRepository.save(otp1);
            }
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setFrom("flowpayonline@gmail.com");
            helper.setSubject("Reset Password OTP");
            helper.setText(updatedHtmlContent, true);
            mailSender.send(message);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


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
    public AgentDto updatePassword(String newPassword, String email) {
        Agent agent = agentRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException("ops !! agent not found "));
        agent.setPassword(newPassword);
        Agent saved =agentRepository.save(agent);
       return AgentDto.toDto(saved);

    }

    @Override
    public boolean verifyEmail(String email) {

        return agentRepository.findByEmail(email).isPresent() ? true :false;
    }



    private int generateOtp(){
        Random random = new Random();
        return random.nextInt(0000,9999);
    }
}
