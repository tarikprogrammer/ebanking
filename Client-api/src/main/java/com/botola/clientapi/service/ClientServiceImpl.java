package com.botola.clientapi.service;


import com.botola.clientapi.dto.AccountDto;
import com.botola.clientapi.dto.ClientDto;
import com.botola.clientapi.dto.ClientDtoAcc;
import com.botola.clientapi.dto.TempraryCardDto;
import com.botola.clientapi.entities.Client;
import com.botola.clientapi.entities.Otp;
import com.botola.clientapi.openfeigns.AgentVerifyEmail;
import com.botola.clientapi.openfeigns.ClientAccount;
import com.botola.clientapi.repositories.ClientRepository;
import com.botola.clientapi.repositories.OtpRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final OtpRepository otpRepository;
    private final AgentVerifyEmail agentVerifyEmail;
    private final JavaMailSender mailSender;
    private final ClientAccount clientAccount;


    @Override
    public boolean createClientByAgent(ClientDto client, MultipartFile file) {
        boolean emailExist = agentVerifyEmail.verifyEmail(client.email());
        if(!emailExist) {

            boolean searchClient = clientRepository.findByEmail(client.email()).isPresent();
            if(!searchClient) {
                System.out.println("I am Here");
                String generatePassword = generatePassword();
                Client client1 = ClientDto.toEntity(client);
                client1.setImageIdentity(file.getOriginalFilename().getBytes(StandardCharsets.UTF_8));
                client1.setPassword(generatePassword);
                client1.setLocked(false);
                Client savedClient = clientRepository.save(client1);

                // send Email to client

                Path path = Paths.get("/Users/tarik/Desktop/ebanking/Client-api/src/main/resources/templates/sendPassword.html");
                try {
                    String htmlContent = new String(Files.readAllBytes(path));
                    Map<String,String> maps = new HashMap<>();
                    maps.put("clientName",savedClient.getLname());
                    maps.put("usernameClient",savedClient.getEmail());
                    maps.put("PasswordClient",generatePassword);
                    String updatedHtmlContent = htmlContent;
                    for (Map.Entry<String, String> entry : maps.entrySet()) {
                        String placeholder = "{{" + entry.getKey() + "}}";
                        updatedHtmlContent = updatedHtmlContent.replace(placeholder, entry.getValue());
                    }


                    MimeMessage message = mailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(message, true);
                    helper.setTo(savedClient.getEmail());
                    helper.setFrom("flowpayonline@gmail.com");
                    helper.setSubject("Congratulations , Your account has been created");
                    helper.setText(updatedHtmlContent, true);
                    mailSender.send(message);

                }catch (Exception e) {
                    e.printStackTrace();
                }

                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteClientByAgent(String email) {
        Client clientSearch = clientRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        clientRepository.delete(clientSearch);
        return verifyEmail(email);

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
        return clientRepository.findByEmail(email).isPresent();
    }

    @Override
    public Page<ClientDtoAcc> findAllClients(Pageable pageable,int page) {
        Page<Client> clients = clientRepository.findAll(PageRequest.of(page,4));
        Page<ClientDtoAcc> clientDtos = clients.map(ClientDtoAcc::toDto);
        clientDtos.getContent().forEach(clientdto -> {
            List<AccountDto> account = clientAccount.getAccounts(clientdto.getId());
            List<TempraryCardDto>tempraryCardDtos = clientAccount.getTemporaryCards(clientdto.getEmail());
            clientdto.setAccountDtoList(account);
            clientdto.setTempraryCardDtos(tempraryCardDtos);
        });
        return clientDtos;
    }



    @Override
    public Client changeVisibility(String email) {
        Client client = clientRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException("ops !! client not found "));
        client.setLocked(!client.isLocked());
        return clientRepository.save(client);
    }

    @Override
    public ClientDtoAcc login(String email, String password) {
        Client client =clientRepository.findByEmailAndPassword(email, password).orElseThrow(()->new EntityNotFoundException("email or password incorrect"));
        ClientDtoAcc clientDtoAcc = new ClientDtoAcc();
        BeanUtils.copyProperties(client, clientDtoAcc);
        List<AccountDto>accountDtos = clientAccount.getAccounts(client.getId());
        clientDtoAcc.setAccountDtoList(accountDtos);
        List<TempraryCardDto> tempraryCardDtos = clientAccount.getTemporaryCards(client.getEmail());
        clientDtoAcc.setTempraryCardDtos(tempraryCardDtos);
        return  clientDtoAcc;
    }

    @Override
    public boolean getClientByEmail(String email) {
        return clientRepository.findByEmail(email).isPresent();
    }

    @Override
    public Long getClientIdByEmail(String email) {
        return clientRepository.findByEmail(email).get().getId();
    }

    @Override
    public String getClientPhoneNumberByEmail(String email) {

        return clientRepository.findByEmail(email).get().getPhone();
    }



    @Override
    public String getClientNameByEmail(String email) {
        return clientRepository.findByEmail(email).get().getLname();
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

    public String getClientById(Long id){
        return clientRepository.findById(id).get().getEmail();
    }

    @Override
    public ClientDtoAcc getClientByEmailAdress(String email) {
        Optional<Client> client = clientRepository.findByEmail(email);
        if (client.isPresent()) {
           Client getClient = client.get();
           ClientDtoAcc clientDtoAcc = new ClientDtoAcc();
           BeanUtils.copyProperties(getClient,clientDtoAcc);
           List<AccountDto>accountDtos = clientAccount.getAccounts(getClient.getId());
           clientDtoAcc.setAccountDtoList(accountDtos);
            List<TempraryCardDto> tempraryCardDtos = clientAccount.getTemporaryCards(getClient.getEmail());
            clientDtoAcc.setTempraryCardDtos(tempraryCardDtos);
            return  clientDtoAcc;

        }

        return null;
    }


}
