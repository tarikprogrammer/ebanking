package com.botola.clientapi.service;


import com.botola.clientapi.dto.ClientDto;
import org.springframework.web.multipart.MultipartFile;

public interface ClientService {
    ClientDto createClientByAgent(ClientDto client, MultipartFile file);
    void deleteClient(String email);
    void sendOTPtoResetPassword(String email);
    boolean verfiyOtp(int otp,String email);
    boolean updatePassword(String newPassword, String email);
    boolean verifyEmail(String email);
}
