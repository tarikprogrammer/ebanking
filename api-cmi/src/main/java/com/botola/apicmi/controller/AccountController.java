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
    public ResponseEntity<String> rechargeAccount(@PathVariable Long accountId, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        if (amount == null || amount <= 0) {
            return ResponseEntity.badRequest().body("Invalid amount provided.");
        }
        boolean success = accountService.rechargeAccount(accountId, amount);
        return success ? ResponseEntity.ok("Account recharged successfully.") :
                ResponseEntity.badRequest().body("Recharge failed. Check account details or plafond.");
    }

    @PostMapping("/retrieveFromAccount/{accountId}")
    public ResponseEntity<String> retrieveFromAccount(@PathVariable Long accountId, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        if (amount == null || amount <= 0) {
            return ResponseEntity.badRequest().body("Invalid amount provided.");
        }
        boolean success = accountService.retrieveFromAccount(accountId, amount);
        return success ? ResponseEntity.ok("Amount retrieved successfully.") :
                ResponseEntity.badRequest().body("Retrieve failed. Check account balance.");
    }

    @GetMapping("/Device/{accountId}")
    public ResponseEntity<?> checkAccountDevice(@PathVariable Long accountId) {
        String device = accountService.checkAccountDevice(accountId);
        if (device != null) {
            return ResponseEntity.ok("Account currency: " + device);
        }
        return ResponseEntity.badRequest().body("Account not found.");
    }

}
