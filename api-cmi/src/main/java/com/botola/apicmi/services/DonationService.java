package com.botola.apicmi.services;


import com.botola.apicmi.dto.DonationDto;
import com.botola.apicmi.entities.Account;
import com.botola.apicmi.entities.TemporaryCrad;
import com.botola.apicmi.repositories.AccountRepo;
import com.botola.apicmi.repositories.TemporarycardRepo;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DonationService {
    private final TemporarycardRepo temporarycardRepo;
    private final AccountRepo accountRepo;
    private final JavaMailSender mailSender;




    private String loadEmailTemplate(String templateName) throws IOException, IOException {
        ClassPathResource resource = new ClassPathResource("templates/" + templateName);
        return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }

    public boolean giveDonation(DonationDto dto){

        Optional<Account> accountOpt = accountRepo.findByIban(dto.getRib());
        if(accountOpt.isPresent()){
            Account account = accountOpt.get();
            double solde = account.getSolde();
            if (solde >= dto.getPrice()){
                solde -=dto.getPrice();
                account.setSolde(solde);
                accountRepo.save(account);

                try {
                    String htmlContent = loadEmailTemplate("donation.html");



                    MimeMessage message = mailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(message, true);
                    helper.setTo(dto.getEmail());
                    helper.setFrom("flowpayonline@gmail.com");
                    helper.setSubject("Congratulations , Your donation has been processed successfully ");
                    helper.setText(htmlContent, true);
                    mailSender.send(message);

                }catch (Exception e) {
                    e.printStackTrace();
                }

                return true;
            }else {
                return false;
            }

        }

        Optional<TemporaryCrad> temporaryCardOpt = temporarycardRepo.findByIban(dto.getRib());

        if(temporaryCardOpt.isPresent()){
            TemporaryCrad temporaryCard = temporaryCardOpt.get();
            double solde = temporaryCard.getSolde();
            if (solde >= dto.getPrice()){
                solde -=dto.getPrice();
                temporaryCard.setSolde(solde);
                temporarycardRepo.save(temporaryCard);

                try {
                    String htmlContent = loadEmailTemplate("donation.html");



                    MimeMessage message = mailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(message, true);
                    helper.setTo(dto.getEmail());
                    helper.setFrom("flowpayonline@gmail.com");
                    helper.setSubject("Congratulations , Your donation has been processed successfully ");
                    helper.setText(htmlContent, true);
                    mailSender.send(message);

                }catch (Exception e) {
                    e.printStackTrace();
                }

                return true;

            }else{
                return false;
            }
        }

        return false;

    }
}
