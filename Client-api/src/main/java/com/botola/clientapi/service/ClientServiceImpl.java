package com.botola.clientapi.service;


import com.botola.clientapi.dto.ClientDto;
import com.botola.clientapi.entities.Otp;
import com.botola.clientapi.openfeigns.AgentVerifyEmail;
import com.botola.clientapi.repositories.ClientRepository;
import com.botola.clientapi.repositories.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final OtpRepository otpRepository;
    private final AgentVerifyEmail agentVerifyEmail;


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


    private String generatePassword() {
        return "FlowPay2024" ;
    }
}
