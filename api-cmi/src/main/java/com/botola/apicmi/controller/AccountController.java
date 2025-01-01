package com.botola.apicmi.controller;

import com.botola.apicmi.dto.AccountDto;
import com.botola.apicmi.service.ServiceAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private ServiceAccount serviceAccount;

    // Get account by ID
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long accountId) {
        AccountDto accountDto = serviceAccount.getAccountById(accountId);
        return ResponseEntity.ok(accountDto);
    }

    // Get account by client ID
    @GetMapping("/client/{clientId}")
    public ResponseEntity<AccountDto> getAccountByClientId(@PathVariable Long clientId) {
        AccountDto accountDto = serviceAccount.getAccountByClientId(clientId);
        return ResponseEntity.ok(accountDto);
    }

    // Create a new account
    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        AccountDto createdAccountDto = serviceAccount.createAccount(accountDto);
        return ResponseEntity.ok(createdAccountDto);
    }

    // Update account balance
    @PutMapping("/{accountId}/balance")
    public ResponseEntity<AccountDto> updateAccountBalance(
            @PathVariable Long accountId,
            @RequestParam double newBalance) {
        AccountDto updatedAccountDto = serviceAccount.updateAccountBalance(accountId, newBalance);
        return ResponseEntity.ok(updatedAccountDto);
    }

    // Withdraw from account
    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<String> withdraw(
            @PathVariable Long accountId,
            @RequestParam double amount) {
        boolean success = serviceAccount.withdraw(accountId, amount);
        if (success) {
            return ResponseEntity.ok("Withdrawal successful");
        } else {
            return ResponseEntity.badRequest().body("Insufficient balance");
        }
    }

    // Deposit to account
    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<AccountDto> deposit(
            @PathVariable Long accountId,
            @RequestParam double amount) {
        AccountDto updatedAccountDto = serviceAccount.deposit(accountId, amount);
        return ResponseEntity.ok(updatedAccountDto);
    }

    // Delete account by ID
    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountId) {
        serviceAccount.deleteAccount(accountId);
        return ResponseEntity.ok("Account deleted successfully");
    }
}
