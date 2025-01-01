package com.botola.apicmi.service;

import com.botola.apicmi.dto.AccountDto;
import com.botola.apicmi.entities.Account;
import com.botola.apicmi.repositories.RepoAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ServiceAccount {

    @Autowired
    private RepoAccount repoAccount;

    // Retrieve account by ID
    public AccountDto getAccountById(Long accountId) {
        Account account = repoAccount.findAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found for ID: " + accountId));
        return AccountDto.toDto(account);
    }

    // Retrieve account by client ID
    public AccountDto getAccountByClientId(Long clientId) {
        Account account = repoAccount.findByClientId(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found for Client ID: " + clientId));
        return AccountDto.toDto(account);
    }

    // Create a new account
    public AccountDto createAccount(AccountDto accountDto) {
        if (repoAccount.existsByClientId(accountDto.getClientId())) {
            throw new IllegalArgumentException("An account already exists for Client ID: " + accountDto.getClientId());
        }
        Account account = AccountDto.toAccount(accountDto);
        Account savedAccount = repoAccount.save(account);
        return AccountDto.toDto(savedAccount);
    }

    // Update account balance
    public AccountDto updateAccountBalance(Long accountId, double newBalance) {
        Account account = repoAccount.findAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found for ID: " + accountId));
        account.setSolde(newBalance);
        Account updatedAccount = repoAccount.save(account);
        return AccountDto.toDto(updatedAccount);
    }

    // Withdraw from account
    public boolean withdraw(Long accountId, double amount) {
        Account account = repoAccount.findAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found for ID: " + accountId));
        if (account.getSolde() < amount) {
            return false; // Insufficient balance
        }
        account.setSolde(account.getSolde() - amount);
        repoAccount.save(account);
        return true;
    }

    // Deposit to account
    public AccountDto deposit(Long accountId, double amount) {
        Account account = repoAccount.findAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found for ID: " + accountId));
        account.setSolde(account.getSolde() + amount);
        Account updatedAccount = repoAccount.save(account);
        return AccountDto.toDto(updatedAccount);
    }

    // Delete account by ID
    public void deleteAccount(Long accountId) {
        if (!repoAccount.existsById(accountId)) {
            throw new IllegalArgumentException("Account not found for ID: " + accountId);
        }
        repoAccount.deleteById(accountId);
    }
}
