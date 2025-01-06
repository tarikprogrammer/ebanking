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

    boolean existsByIban(String iban);

    boolean existsByCvv(String cvv);

    List<Account> findByIbanAndDeviceIn(String iban, List<String> cryptoDevices);
    List<Account> findByIbanAndDeviceNotIn(String iban, List<String> cryptoDevices);

    List<Account> findByClientIdAndDeviceIn(Long clientId, List<String> cryptoDevices);

    List<Account> findByClientIdAndDeviceNotIn(Long clientId, List<String> cryptoDevices);
}

