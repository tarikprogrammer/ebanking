package com.botola.clientapi.service;


import com.botola.clientapi.dto.ClientDto;
import com.botola.clientapi.entities.Client;
import com.botola.clientapi.entities.Otp;
import com.botola.clientapi.openfeigns.AgentVerifyEmail;
import com.botola.clientapi.repositories.ClientRepository;
import com.botola.clientapi.repositories.OtpRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final OtpRepository otpRepository;
    private final AgentVerifyEmail agentVerifyEmail;
    private final JavaMailSender mailSender;


    @Override
    public ClientDto createClientByAgent(ClientDto client, MultipartFile file) {
       // client  database
        // agent  database
        boolean emailExist = agentVerifyEmail.verifyEmail(client.email());

        // email : belaid.tarikk@gmail.com

        return null;
    }

    @Override
    public void deleteClient(String email) {

    }

    @Override
    public void sendOTPtoResetPassword(String email) {
        Client client = clientRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException("Email not found"));
        Path path = Paths.get("/Users/tarik/Desktop/ebanking/Agentapi/src/main/resources/templates/sendLinkPassword.html");
        try {
            String htmlContent = new String(Files.readAllBytes(path));
            int otp = generateOtp();
            String updatedHtmlContent = htmlContent.replace("{{otp}}", String.valueOf(otp));
            if(client.getOtp() ==null){
                Otp otpDto = Otp.builder()
                        .otp(otp)
                        .expireOtp(LocalDateTime.now().plusMinutes(2))
                        .client(client)
                        .build();
                otpRepository.save(otpDto);
            }else{
                Otp otp1 =client.getOtp();
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
    public boolean verfiyOtp(int otp, String email) {
        Client agent = clientRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException("ops !! agent not found "));
        int OTP= agent.getOtp().getOtp();
        LocalDateTime now = LocalDateTime.now();
        if(otp == OTP && now.isBefore(agent.getOtp().getExpireOtp())){
            return true;
        }

        return false;
    }

    @Override
    public boolean updatePassword(String newPassword, String email) {
        Client agent = clientRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException("ops !! agent not found "));
        agent.setPassword(newPassword);
        clientRepository.save(agent);

        return true;
    }

    @Override
    public boolean verifyEmail(String email) {
        return clientRepository.findByEmail(email).isPresent() ? true :false;
    }

  // generate a unique password
    private String generatePassword() {
        return "FlowPay2024" ;
    }
  // generate random otp
    private int generateOtp(){
        Random random = new Random();
        return random.nextInt(0000,9999);
    }
}
