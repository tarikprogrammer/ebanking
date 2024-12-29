package com.botola.clientapi.service;


import com.botola.clientapi.dto.ClientDto;

public interface AccountService {

    boolean openAccountByAgent(ClientDto clientDto,String accountType);
    boolean closeAccountByAgent(ClientDto clientDto);


}
