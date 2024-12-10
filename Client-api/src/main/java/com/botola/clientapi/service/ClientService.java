package com.botola.clientapi.service;


import com.botola.clientapi.dto.ClientDto;

public interface ClientService {
    ClientDto createClient(ClientDto client);
    void deleteClient(String email);
}
