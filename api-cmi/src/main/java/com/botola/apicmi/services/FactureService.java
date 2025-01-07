package com.botola.apicmi.services;


import com.botola.apicmi.dto.FactureDto;
import com.botola.apicmi.entities.Account;
import com.botola.apicmi.entities.Facture;
import com.botola.apicmi.entities.TemporaryCrad;
import com.botola.apicmi.openfeigns.ClientConn;
import com.botola.apicmi.repositories.AccountRepo;
import com.botola.apicmi.repositories.FactureRepo;
import com.botola.apicmi.repositories.TemporarycardRepo;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class FactureService {
    private final FactureRepo factureRepo;
    private final TemporarycardRepo temporarycardRepo;
    private final AccountRepo accountRepo;
    private final JavaMailSender mailSender;
    private final ClientConn clientConn;




    private String loadEmailTemplate(String templateName) throws IOException, IOException {
        ClassPathResource resource = new ClassPathResource("templates/" + templateName);
        return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }

    public FactureDto getFactureByCode(String code) {
        Facture facture = factureRepo.findByFactureCode(code).get();
        FactureDto factureDto = new FactureDto();
        if(facture!=null){
            BeanUtils.copyProperties(facture, factureDto);
        }
        return factureDto;
    }



    public boolean payerFacture(FactureDto factureDto,String rib) {
        Facture facture = factureRepo.findByFactureCode(factureDto.getFactureCode()).get();
        if (facture != null) {
            Account account = accountRepo.findByIban(rib).get();
            if(account != null) {
                double solde = account.getSolde();
                if(solde>=facture.getPrice()){
                    solde -= facture.getPrice();
                    account.setSolde(solde);
                    accountRepo.save(account);
                    facture.setNonPaye(false);
                    Facture facturesaved = factureRepo.save(facture);





                    try {
                        String htmlContent = loadEmailTemplate("FacturePayment.html");
                        Map<String,String> maps = new HashMap<>();
                        maps.put("nfacture",facturesaved.getFactureCode());
                        maps.put("amount", String.valueOf(facturesaved.getPrice()));
                        String updatedHtmlContent = htmlContent;
                        for (Map.Entry<String, String> entry : maps.entrySet()) {
                            String placeholder = "{{" + entry.getKey() + "}}";
                            updatedHtmlContent = updatedHtmlContent.replace(placeholder, entry.getValue());
                        }


                        MimeMessage message = mailSender.createMimeMessage();
                        MimeMessageHelper helper = new MimeMessageHelper(message, true);
                        helper.setTo(clientConn.getClientById(account.getClientId()));
                        helper.setFrom("flowpayonline@gmail.com");
                        helper.setSubject("Congratulations , Your Payment has been processed successfully ");
                        helper.setText(updatedHtmlContent, true);
                        mailSender.send(message);

                    }catch (Exception e) {
                        e.printStackTrace();
                    }





                    return true;
                }else {
                    return false;
                }

            }
            Optional<TemporaryCrad>temporaryCradOptional = temporarycardRepo.findByIban(rib);
            if(temporaryCradOptional != null) {
                TemporaryCrad temporaryCrad = temporaryCradOptional.get();
                double solde = temporaryCrad.getSolde();
                if(solde>=facture.getPrice()){
                    solde -= facture.getPrice();
                    temporaryCrad.setSolde(solde);
                    temporarycardRepo.save(temporaryCrad);
                    facture.setNonPaye(false);
                    Facture facturesaved = factureRepo.save(facture);





                    try {
                        String htmlContent = loadEmailTemplate("FacturePayment.html");
                        Map<String,String> maps = new HashMap<>();
                        maps.put("nfacture",facturesaved.getFactureCode());
                        maps.put("amount", String.valueOf(facturesaved.getPrice()));
                        String updatedHtmlContent = htmlContent;
                        for (Map.Entry<String, String> entry : maps.entrySet()) {
                            String placeholder = "{{" + entry.getKey() + "}}";
                            updatedHtmlContent = updatedHtmlContent.replace(placeholder, entry.getValue());
                        }


                        MimeMessage message = mailSender.createMimeMessage();
                        MimeMessageHelper helper = new MimeMessageHelper(message, true);
                        helper.setTo(temporaryCrad.getEmail());
                        helper.setFrom("flowpayonline@gmail.com");
                        helper.setSubject("Congratulations , Your Payment has been processed successfully ");
                        helper.setText(updatedHtmlContent, true);
                        mailSender.send(message);

                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }else {
                    return false;
                }
            }
        }
        return false;
    }



}
