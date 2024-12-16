package com.botola.clientapi.controller;


import com.botola.clientapi.openfeigns.AgentVerifyEmail;
import com.botola.clientapi.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.internal.build.AllowNonPortable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client-api")
@RequiredArgsConstructor
public class ClientController {

    private final  ClientService clientService;
    private final  AgentVerifyEmail agentVerifyEmail;

    @GetMapping("/client/{emaiL}")
    public ResponseEntity<Boolean> getClient(@PathVariable String emaiL) {
        return ResponseEntity.ok(agentVerifyEmail.verifyEmail(emaiL));
    }


}
