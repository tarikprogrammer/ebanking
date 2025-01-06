package com.botola.apicmi.services;



import com.botola.apicmi.dto.TempraryCardDto;
import com.botola.apicmi.entities.Account;
import com.botola.apicmi.entities.TemporaryCrad;
import com.botola.apicmi.openfeigns.ClientConn;
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

import java.util.*;

@Service
@RequiredArgsConstructor
public class TempraryCardService {

    private final TemporarycardRepo temporarycardRepo;

    private final ClientConn clientConn;

    private final AccountRepo accountRepo;

    private final JavaMailSender mailSender;



    private String loadEmailTemplate(String templateName) throws IOException, IOException {
        ClassPathResource resource = new ClassPathResource("templates/" + templateName);
        return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }


    public boolean createTempraryCard(TempraryCardDto tempraryCardDto) {

        Long  clientId = clientConn.getClient(tempraryCardDto.getEmail());


        if(clientId != null) {

            List<Account> getAccounts = accountRepo.findByClientId(clientId);

            Account acc = getAccounts.stream().filter((account -> account.getDevice().equals(tempraryCardDto.getChoixCompte()))).findFirst().get();

            if(acc.getSolde()>=tempraryCardDto.getSolde() && acc.getPlafond()>tempraryCardDto.getSolde()){

                acc.setSolde(acc.getSolde()-tempraryCardDto.getSolde());
                accountRepo.save(acc);

                String cvv = generateCVV();

                boolean checkIfCvvExistInAccount = false;

                boolean checkIfCvvExistInTemporaryCard = false;


                do{
                    checkIfCvvExistInAccount = accountRepo.findByCvv(cvv).isPresent();
                    checkIfCvvExistInTemporaryCard =temporarycardRepo.findByCvv(cvv).isPresent();

                    if(checkIfCvvExistInAccount || checkIfCvvExistInTemporaryCard){
                        cvv = generateCVV();
                    }


                }while(checkIfCvvExistInAccount || checkIfCvvExistInTemporaryCard);



                TemporaryCrad temporaryCrad = TemporaryCrad.builder()

                        .lname(tempraryCardDto.getLname())
                        .email(tempraryCardDto.getEmail())
                        .solde(tempraryCardDto.getSolde())
                        .fname(tempraryCardDto.getFname())
                        .choixCompte(tempraryCardDto.getChoixCompte())
                        .expireAt(tempraryCardDto.getExpireAt())
                        .clientID(clientId)
                        .phone(tempraryCardDto.getPhone())
                        .iban(generateIban())
                        .cvv(cvv)
                        .build();

                TemporaryCrad tem =temporarycardRepo.save(temporaryCrad);






                try {
                    String htmlContent = loadEmailTemplate("cardTemporary.html");
                    Map<String,String> maps = new HashMap<>();
                    maps.put("username",tempraryCardDto.getEmail());
                    maps.put("rib",tem.getIban());
                    maps.put("cvv",tem.getCvv());
                    maps.put("expire",tem.getExpireAt());
                    maps.put("clientName",tem.getFname());
                    String updatedHtmlContent = htmlContent;
                    System.out.println(maps);
                    for (Map.Entry<String, String> entry : maps.entrySet()) {
                        String placeholder = "{{" + entry.getKey() + "}}";
                        System.out.println(placeholder);
                        updatedHtmlContent = updatedHtmlContent.replace(placeholder, entry.getValue());
                    }


                    MimeMessage message = mailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(message, true);
                    helper.setTo(tem.getEmail());
                    helper.setFrom("flowpayonline@gmail.com");
                    helper.setSubject("Congratulations , Your Bank temporary card has been created");
                    helper.setText(updatedHtmlContent, true);
                    mailSender.send(message);

                }catch (Exception e) {
                    e.printStackTrace();
                }


                return  true;


            }


        }


        return false;
    }



    public String generateCVV(){
        Random rd = new Random();
        String s = "1234567890";
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<3;i++){
            sb.append(rd.nextInt(s.length()));
        }

        return sb.toString();

    }

    public String generateIban() {
        String s = "0123456789";
        Random rd = new Random();
        StringBuilder sb = new StringBuilder();

        int ibanLength = 24;
        for (int i = 0; i < ibanLength; i++) {

            char randomChar = s.charAt(rd.nextInt(s.length()));
            sb.append(randomChar);
        }

        return sb.toString();
    }



    public List<TemporaryCrad>getTempraryCards(String email){
        return temporarycardRepo.findByEmail(email);
    }
}
