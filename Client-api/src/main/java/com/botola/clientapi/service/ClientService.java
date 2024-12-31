package com.botola.clientapi.service;


import com.botola.clientapi.dto.ClientDto;
import com.botola.clientapi.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ClientService {
    boolean createClientByAgent(ClientDto client, MultipartFile file);
     boolean deleteClientByAgent(String email);
     void sendOTPtoResetPassword(String email);
    boolean verfiyOtp(int otp,String email);
    boolean updatePassword(String newPassword, String email);
    boolean verifyEmail(String email);
    Page<Client> findAllClients(Pageable pageable,int page);
    Client changeVisibility(String email);
    Client login(String email, String password);
}