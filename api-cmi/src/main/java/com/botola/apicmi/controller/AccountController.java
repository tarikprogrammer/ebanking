package com.botola.apicmi.controller;


import com.botola.apicmi.dto.AccountDto;
import com.botola.apicmi.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/accounts")

@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;



    @PostMapping("/openAccountByAgent/{email}")
    public ResponseEntity<?> openAccountByAgent(@PathVariable String email, @RequestBody AccountDto accountDto) {
        return ResponseEntity.ok(accountService.openAccountByAgent(accountDto,email));
    }

    @GetMapping("/getClientID/{clientId}")
    public ResponseEntity<?> getAccountInfos(@PathVariable Long clientId) {
        return ResponseEntity.ok(accountService.accounts(clientId));
    }




}
