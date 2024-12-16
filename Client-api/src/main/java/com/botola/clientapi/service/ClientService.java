package com.botola.clientapi.service;


import com.botola.clientapi.dto.ClientDto;
import org.springframework.web.multipart.MultipartFile;

public interface ClientService {
    ClientDto createClientByAgent(ClientDto client, MultipartFile file);
    void deleteClient(String email);
}
