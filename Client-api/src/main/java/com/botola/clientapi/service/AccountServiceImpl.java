package com.botola.clientapi.service;


import com.botola.clientapi.dto.ClientDto;
import com.botola.clientapi.entities.Account;
import com.botola.clientapi.entities.Client;
import com.botola.clientapi.openfeigns.AgentVerifyEmail;
import com.botola.clientapi.repositories.AccountRepository;
import com.botola.clientapi.repositories.ClientRepository;
import com.botola.clientapi.repositories.OtpRepository;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl  implements AccountService {
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;
    private final JavaMailSender mailSender;


    @Override
    public boolean openAccountByAgent(ClientDto clientDto, String accountType) {
        Client client = Client.builder()
                .fname(clientDto.fname())
                .lname(clientDto.lname())
                .email(clientDto.email())
                .phone(clientDto.phone())
                .build();
        Boolean searchClient = clientRepository.findByEmail(client.getEmail()).isPresent();
        Client client1 = clientRepository.findByEmail(client.getEmail()).orElseThrow(()-> new EntityNotFoundException("client not found"));
        if (searchClient && client1.getAccount() == null) {
            Account account = Account.builder()
                    .accountName(accountType)
                    .plafond(detectePlafond(accountType))
                    .balance(detectePlafond(accountType))
                    .iban(generateIban(client.getPhone()))
                    .client(client1)
                    .build();
            Account savedAccount =accountRepository.save(account);

            Path path = Paths.get("/Users/tarik/Desktop/ebanking/Client-api/src/main/resources/templates/accountOpening.html");
            try {
                String htmlContent = new String(Files.readAllBytes(path));
                Map<String,String> maps = new HashMap<>();
                maps.put("username",savedAccount.getClient().getEmail());
                maps.put("accountType", savedAccount.getAccountName());
                maps.put("rib",savedAccount.getIban().substring(10));
                maps.put("clientName",savedAccount.getClient().getLname());
                String updatedHtmlContent = htmlContent;
                for (Map.Entry<String, String> entry : maps.entrySet()) {
                    String placeholder = "{{" + entry.getKey() + "}}";
                    updatedHtmlContent = updatedHtmlContent.replace(placeholder, entry.getValue());
                }


                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setTo(savedAccount.getClient().getEmail());
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

    @Override
    public boolean closeAccountByAgent(ClientDto clientDto) {
        return false;
    }



    public String generateIban(String phoneNumber) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            Random rd = new Random();
            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(phoneNumber, "");
            String regionCode = phoneUtil.getRegionCodeForNumber(numberProto);
            StringBuilder str = new StringBuilder();
            for(int i=0;i<25;i++){
                str.append(rd.nextInt(10));
            }
            String  iban = regionCode+str.toString();
            String ibanFormat = iban.replaceAll("(.{4})", "$1 ").trim();
            return ibanFormat;
        }catch (Exception e) {
           return e.getMessage();
        }

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
}
