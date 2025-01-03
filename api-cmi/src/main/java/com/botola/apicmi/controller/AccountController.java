package com.botola.apicmi.controller;

import com.botola.apicmi.dto.AccountDto;
import com.botola.apicmi.service.ServiceAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/accounts")

@RequiredArgsConstructor
public class AccountController {

    private final ServiceAccount accountService;



    @PostMapping("/openAccountByAgent/{email}")
    public ResponseEntity<?> openAccountByAgent(@PathVariable String email, @RequestBody AccountDto accountDto) {
        return ResponseEntity.ok(accountService.openAccountByAgent(accountDto,email));
    }

    @GetMapping("/getClientID/{clientId}")
    public ResponseEntity<?> getAccountInfos(@PathVariable Long clientId) {
        return ResponseEntity.ok(accountService.accounts(clientId));
    }

    @PostMapping("/rechargeAccount/{accountId}")
    public ResponseEntity<Boolean> rechargeAccount(@PathVariable Long accountId, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        if (amount == null || amount <= 0) {
            return ResponseEntity.badRequest().body(false); // Invalid input
        }
        boolean success = accountService.rechargeAccount(accountId, amount);
        return ResponseEntity.ok(success); // True if successful, False otherwise
    }

    @PostMapping("/retrieveFromAccount/{accountId}")
    public ResponseEntity<Boolean> retrieveFromAccount(@PathVariable Long accountId, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        if (amount == null || amount <= 0) {
            return ResponseEntity.badRequest().body(false); // Invalid input
        }
        boolean success = accountService.retrieveFromAccount(accountId, amount);
        return ResponseEntity.ok(success); // True if successful, False otherwise
    }

    @GetMapping("/Device/{accountId}")
    public ResponseEntity<?> checkAccountDevice(@PathVariable Long accountId) {
        String device = accountService.checkAccountDevice(accountId);
        if (device != null) {
            return ResponseEntity.ok("Account currency: " + device);
        }
        return ResponseEntity.badRequest().body("Account not found.");
    }

    @GetMapping("/getPlafond/{accountId}")
    public ResponseEntity<Double> getPlafond(@PathVariable Long accountId) {
        Double plafond = accountService.getPlafond(accountId);
        if (plafond != null) {
            return ResponseEntity.ok(plafond);
        } else {
            return ResponseEntity.notFound().build(); // Account not found
        }
    }

}
