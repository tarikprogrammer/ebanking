package com.botola.clientapi.controller;


import com.botola.clientapi.dto.ClientDto;
import com.botola.clientapi.entities.Client;
import com.botola.clientapi.openfeigns.AgentVerifyEmail;
import com.botola.clientapi.service.AccountService;
import com.botola.clientapi.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.internal.build.AllowNonPortable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/client-api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ClientController {

    private final  ClientService clientService;
    private final  AgentVerifyEmail agentVerifyEmail;
    private final AccountService accountService;

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






}
