package com.botola.apicmi.repositories;

import com.botola.apicmi.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {
    List<Account> findByClientId(Long clientId);
    Optional<Account>findByIban(String iban);
    Optional<Account>findByCvv(String cvv);
    List<Account> findByClientIdAndAccountName(Long id, String accountName);
}

