package com.botola.apicmi.Repository;

import com.botola.apicmi.entities.Account;
import com.botola.apicmi.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FactureRepository extends JpaRepository<Facture, Long> {
    // Trouver les factures par compte
    List<Facture> findByAccount(Account account);
}




