package com.botola.apicmi.repositories;

import  com.botola.apicmi.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface RepoAccount extends JpaRepository<Account,Long> {
    Optional<Account> findByClientId(Long clientId);

    Optional<Account> findAccountById(Long accountId);

    Optional<Account> findAccountByIban(String Iban);

    boolean existsByClientId(Long clientId);
}
