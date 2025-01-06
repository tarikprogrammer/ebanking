package com.botola.apicmi.services;


import com.botola.apicmi.dto.AccountDto;
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

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepo accountRepository;
    private final JavaMailSender mailSender;
    private final ClientConn clientConn;
    private final TemporarycardRepo temporarycardRepo;


    private String loadEmailTemplate(String templateName) throws IOException, IOException {
        ClassPathResource resource = new ClassPathResource("templates/" + templateName);
        return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }



    public boolean openAccountByAgent(AccountDto accountDto, String email) {
        boolean clientExists = clientConn.getClientByemail(email);
        if (!clientExists) {
            System.out.println("Client does not exist.");
            return false; // Client not found
        }

        Long clientId = clientConn.getClient(email);
        List<Account> existingAccounts = accountRepository.findByClientIdAndAccountName(clientId, accountDto.getAccountName());
        if (!existingAccounts.isEmpty()) {
            System.out.println("Account already exists for client.");
            return false; // Account already exists
        }

        String pattern = "dd/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String[] currencies = {"MAD", "EUR", "USD", "BTC", "ETH", "LTC"};
        Map<String, Account> savedAccounts = new HashMap<>();

        for (String currency : currencies) {
            try {
                System.out.println("Processing currency: " + currency);
                String iban = generateUniqueIban();
                System.out.println("Generated IBAN: " + iban);

                String cvv = generateUniqueCVV();
                System.out.println("Generated CVV: " + cvv);

                double plafond = determinePlafond(accountDto.getAccountName(), currency);
                double solde = currency.equals("MAD") ? plafond : 0;

                Account account = Account.builder()
                        .accountName(accountDto.getAccountName())
                        .clientId(clientId)
                        .device(currency)
                        .solde(solde)
                        .plafond(plafond)
                        .iban(iban)
                        .expireAt(simpleDateFormat.format(new Date()))
                        .cvv(cvv)
                        .build();

                // Save account individually
                Account savedAccount = accountRepository.save(account);
                savedAccounts.put(currency, savedAccount);
                System.out.println("Account saved: " + savedAccount);

            } catch (Exception e) {
                System.err.println("Error saving account for currency: " + currency);
                e.printStackTrace();
            }
        }

        if (!savedAccounts.isEmpty()) {
            System.out.println("Accounts saved successfully. Sending email...");
            sendAccountOpeningEmail(email, accountDto.getAccountName(), savedAccounts);
        } else {
            System.out.println("No accounts were saved.");
        }

        return !savedAccounts.isEmpty();
    }



    public String generateUniqueIban() {
        // Generate the rest of the IBAN
        Random rd = new Random();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 24; i++) {
            str.append(rd.nextInt(10));
        }

        String iban = str.toString();
        return iban;
        //return iban.replaceAll("(.{4})", "$1 ").trim(); // Format IBAN into groups of 4
    }


    private String generateUniqueCVV() {
        Random rd = new Random();
        String cvv;
        boolean isUnique;

        do {
            // Generate a random 3-digit CVV
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                sb.append(rd.nextInt(10));
            }
            cvv = sb.toString();

            // Check if the CVV is unique in the database
            isUnique = accountRepository.findByCvv(cvv).isEmpty();
        } while (!isUnique);

        return cvv;
    }

    private double determinePlafond(String accountType, String currency) {
        switch (currency) {
            case "MAD":
                return detectePlafond(accountType);
            case "EUR":
                return detectePlafondEUR(accountType);
            case "USD":
                return detectePlafondDOLAR(accountType);
            default: // Crypto currencies
                return 10000;
        }
    }

    private void sendAccountOpeningEmail(String email, String accountName, Map<String, Account> accounts) {

        try {
            String htmlContent = loadEmailTemplate("accountOpening.html");
            Map<String, String> placeholders = new HashMap<>();
            placeholders.put("username", email);
            placeholders.put("accountType", accountName);
            placeholders.put("clientName", clientConn.getClientName(email));

            for (Map.Entry<String, Account> entry : accounts.entrySet()) {
                String currency = entry.getKey();
                Account account = entry.getValue();
                placeholders.put("rib" + currency, account.getIban());
                placeholders.put("cvv" + currency, account.getCvv());
                placeholders.put("expire" + currency, account.getExpireAt());
            }

            String updatedHtmlContent = htmlContent;
            for (Map.Entry<String, String> entry : placeholders.entrySet()) {
                updatedHtmlContent = updatedHtmlContent.replace("{{" + entry.getKey() + "}}", entry.getValue());
            }

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setFrom("flowpayonline@gmail.com");
            helper.setSubject("Congratulations, Your Bank Account Has Been Opened");
            helper.setText(updatedHtmlContent, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
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


    public List<Account> accounts(Long clientId){
        return accountRepository.findByClientId(clientId);
    }
    public List<Account> getCryptoAccounts(Long clientId) {
        // Assuming crypto accounts are determined by specific device types
        List<String> cryptoDevices = Arrays.asList("BTC", "ETH", "LTC");
        return accountRepository.findByClientIdAndDeviceIn(clientId, cryptoDevices);
    }

    public List<Account> getNonCryptoAccounts(Long clientId) {
        // Assuming non-crypto accounts are all devices except those in cryptoDevices
        List<String> cryptoDevices = Arrays.asList("BTC", "ETH", "LTC");
        return accountRepository.findByClientIdAndDeviceNotIn(clientId, cryptoDevices);
    }


    public boolean rechargeAccount(String iban, Double amount) {
        Optional<Account> optionalAccount = accountRepository.findByIban(iban);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            if (account.getSolde() + amount > account.getPlafond()) {
                return false; // Exceeds plafond
            }
            account.setSolde(account.getSolde() + amount);
            accountRepository.save(account);
            return true;
        }
        return false; // Account not found
    }

    public boolean retrieveFromAccount(String iban, Double amount) {
        Optional<Account> optionalAccount = accountRepository.findByIban(iban);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            if (account.getSolde() < amount) {
                return false; // Insufficient funds
            }
            account.setSolde(account.getSolde() - amount);
            accountRepository.save(account);
            return true;
        }
        return false; // Account not found
    }

    public String checkAccountDevice(String iban) {
        Optional<Account> accountOpt = accountRepository.findByIban(iban);
        return accountOpt.map(Account::getDevice).orElse(null);
    }

    public Double getPlafond(String iban) {
        Optional<Account> optionalAccount = accountRepository.findByIban(iban);
        return optionalAccount.map(Account::getPlafond).orElse(null); // Returns null if account not found
    }



}
