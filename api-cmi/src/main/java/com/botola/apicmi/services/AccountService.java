package com.botola.apicmi.services;


import com.botola.apicmi.dto.AccountDto;
import com.botola.apicmi.entities.Account;
import com.botola.apicmi.openfeigns.ClientConn;
import com.botola.apicmi.repositories.AccountRepo;

import jakarta.mail.internet.MimeMessage;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepo accountRepository;
    private final JavaMailSender mailSender;
    private final ClientConn clientConn;


    private String loadEmailTemplate(String templateName) throws IOException, IOException {
        ClassPathResource resource = new ClassPathResource("templates/" + templateName);
        return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }



    public boolean openAccountByAgent(AccountDto accountDto,String email) {
        boolean searchClient = clientConn.getClientByemail(email);
        String pattern = "dd/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        List<Account> accounts= accountRepository.findByClientIdAndAccountName(clientConn.getClient(email), accountDto.getAccountName());
        System.out.println(accounts);
        if(searchClient && accounts.isEmpty()) {
            String iban = generateIban();
            boolean findAcountIban =false;

            do{
                findAcountIban = accountRepository.findByIban(iban).isPresent();
                System.out.println("findAcountIban "+findAcountIban);
                 if(findAcountIban) {
                     iban =generateIban();
                 }
            }while (findAcountIban);

            String generateCVV = generateCVV();
            boolean findAccountCVV =false;
            do{
                findAccountCVV = accountRepository.findByCvv(generateCVV).isPresent();
                if(findAccountCVV) {
                    generateCVV = generateCVV();
                }

              /*  System.out.println("findAccountCVV "+findAccountCVV);*/
            }while(findAccountCVV);


            // first account en MAD
            Account accountMAD = Account.builder()
                    .accountName(accountDto.getAccountName())
                    .clientId(clientConn.getClient(email))
                    .device("MAD")
                    .solde(detectePlafond(accountDto.getAccountName()))
                    .plafond(detectePlafond(accountDto.getAccountName()))
                    .iban(iban)
                    .expireAt(simpleDateFormat.format(new Date()))
                    .cvv(generateCVV)
                    .build();

            Account savedAccountMAD =accountRepository.save(accountMAD);


            // second Account en EUR


             iban = generateIban();
             findAcountIban =false;

            do{
                findAcountIban = accountRepository.findByIban(iban).isPresent();
                if(findAcountIban) {
                    iban =generateIban();
                    System.out.println("iban in "+iban);
                }

               /* System.out.println("findAcountIban "+findAcountIban);*/
            }while (findAcountIban);

             generateCVV = generateCVV();
             findAccountCVV =false;
            do{
                findAccountCVV = accountRepository.findByCvv(generateCVV).isPresent();
                if(findAccountCVV) {
                    generateCVV = generateCVV();
                }
                /*System.out.println("findAccountCVV "+findAccountCVV);*/
            }while(findAccountCVV);


            Account accountEUR = Account.builder()
                    .accountName(accountDto.getAccountName())
                    .clientId(clientConn.getClient(email))
                    .device("EUR")
                    .solde(0)
                    .plafond(detectePlafondEUR(accountDto.getAccountName()))
                    .iban(iban)
                    .expireAt(simpleDateFormat.format(new Date()))
                    .cvv(generateCVV)
                    .build();

            Account savedAccountEUR =accountRepository.save(accountEUR);



            // second Account en DOLAR


            iban = generateIban();
            findAcountIban =false;

            do{
                findAcountIban = accountRepository.findByIban(iban).isPresent();
                if(findAcountIban) {
                    iban =generateIban();
                }
            }while (findAcountIban);

            generateCVV = generateCVV();
            findAccountCVV =false;
            do{
                findAccountCVV = accountRepository.findByCvv(generateCVV).isPresent();
                if(findAccountCVV) {
                    generateCVV = generateCVV();
                }
            }while(findAccountCVV);


            Account accountDOLOAR = Account.builder()
                    .accountName(accountDto.getAccountName())
                    .clientId(clientConn.getClient(email))
                    .device("USD")
                    .solde(0)
                    .plafond(detectePlafondDOLAR(accountDto.getAccountName()))
                    .iban(iban)
                    .expireAt(simpleDateFormat.format(new Date()))
                    .cvv(generateCVV)
                    .build();

            Account savedAccountDOLAR =accountRepository.save(accountDOLOAR);






            try {
                String htmlContent = loadEmailTemplate("accountOpening.html");
                Map<String,String> maps = new HashMap<>();
                maps.put("username",email);
                maps.put("accountType", savedAccountMAD.getAccountName());
                maps.put("ribMAD",savedAccountMAD.getIban());
                maps.put("ribEUR",savedAccountEUR.getIban());
                maps.put("ribUSD",savedAccountDOLAR.getIban());
                maps.put("cvvMAD",savedAccountMAD.getCvv());
                maps.put("cvvEUR",savedAccountEUR.getCvv());
                maps.put("cvvUSD",savedAccountDOLAR.getCvv());
                maps.put("expireMAD",savedAccountMAD.getExpireAt());
                maps.put("expireEUR",savedAccountEUR.getExpireAt());
                maps.put("expireUSD",savedAccountDOLAR.getExpireAt());
                maps.put("clientName",clientConn.getClientName(email));
                String updatedHtmlContent = htmlContent;
                for (Map.Entry<String, String> entry : maps.entrySet()) {
                    String placeholder = "{{" + entry.getKey() + "}}";
                    updatedHtmlContent = updatedHtmlContent.replace(placeholder, entry.getValue());
                }


                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setTo(email);
                helper.setFrom("flowpayonline@gmail.com");
                helper.setSubject("Congratulations , Your Bank account has been opened");
                helper.setText(updatedHtmlContent, true);
                mailSender.send(message);

            }catch (Exception e) {
                e.printStackTrace();
            }


            return true;

        }
        return false;
    }





    public String generateIban() {
        String s = "0123456789"; // Chiffres autorisés pour l'IBAN
        Random rd = new Random();
        StringBuilder sb = new StringBuilder();

        int ibanLength = 24; // Longueur totale d'un IBAN typique
        for (int i = 0; i < ibanLength; i++) {
            // Sélectionne un caractère aléatoire dans `s`
            char randomChar = s.charAt(rd.nextInt(s.length()));
            sb.append(randomChar);
        }

        return sb.toString();
    }

    public double detectePlafond(String accountType){
        switch (accountType){
            case "Hssab1":
                return 200.0;
            case "Hssab2":
                return 10000;
            case "Hssab3":
                return 15000;
            case "Hssab4":
                return 20000;
            default:
                return 0.0;
        }

    }

    public double detectePlafondEUR(String accountType){
        switch (accountType){
            case "Hssab1":
                return 20;
            case "Hssab2":
                return 1000;
            case "Hssab3":
                return 1500;
            case "Hssab4":
                return 2000;
            default:
                return 0.0;
        }

    }

    public double detectePlafondDOLAR(String accountType){
        switch (accountType){
            case "Hssab1":
                return 20;
            case "Hssab2":
                return 1000;
            case "Hssab3":
                return 1500;
            case "Hssab4":
                return 2000;
            default:
                return 0.0;
        }

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

    public List<Account>accounts(Long clientId){
        return accountRepository.findByClientId(clientId);
    }


}
