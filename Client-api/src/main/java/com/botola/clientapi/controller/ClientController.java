package com.botola.clientapi.controller;


import com.botola.clientapi.dto.ClientDto;
import com.botola.clientapi.entities.TemporaryCard;
import com.botola.clientapi.openfeigns.AgentVerifyEmail;

import com.botola.clientapi.service.AccountService;
import com.botola.clientapi.service.ClientService;
import com.botola.clientapi.service.TemporaryCardService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/client-api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ClientController {

    private final  ClientService clientService;
    private final  AgentVerifyEmail agentVerifyEmail;
    private final AccountService accountService;
    private final TemporaryCardService temporaryCardService;

    @GetMapping("/client/{emaiL}")
    public ResponseEntity<Boolean> getClient(@PathVariable String emaiL) {
        if(agentVerifyEmail.verifyEmail(emaiL)) {
            return ResponseEntity.ok(true);
        }
        if (clientService.verifyEmail(emaiL)) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }


    @PostMapping(value = "/client/save",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveClient(@RequestPart(name = "client") String clientJson, @RequestPart("file") MultipartFile file) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ClientDto clientDto = objectMapper.readValue(clientJson, ClientDto.class);

            return ResponseEntity.ok( clientService.createClientByAgent(clientDto,file));
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/client/delete/{email}")
    public boolean deleteClient(@PathVariable String email) {
       return clientService.deleteClientByAgent(email);
    }



    @PostMapping("/client/openAccount/{accountType}")
    public ResponseEntity<?>openAccountByAgent(@RequestBody ClientDto clientDto,@PathVariable("accountType") String accountType) {
        return ResponseEntity.ok(accountService.openAccountByAgent(clientDto,accountType));
    }


    @GetMapping("/client/clients/{page}")
    public ResponseEntity<?> getClients(@PathVariable int page, Pageable pageable) {
        return ResponseEntity.ok(clientService.findAllClients(pageable,page));
    }


    @GetMapping("/client/locked/{email}")
    public ResponseEntity<?> lockClient(@PathVariable String email) {
        return ResponseEntity.ok(clientService.changeVisibility(email));
    }


    @PostMapping("/client/temoraryCard/{email}")
    public ResponseEntity<?> temoraryCard(@PathVariable String email,@RequestBody TemporaryCard temporaryCard ) {
        return ResponseEntity.ok(temporaryCardService.addTemporaryCard(temporaryCard,email));
    }


    @PostMapping("/client/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> login) {
        return ResponseEntity.ok(clientService.login(login.get("email"),login.get("password")));
    }






}
