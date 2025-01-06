package com.botola.apicmi.repositories;

import com.botola.apicmi.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FactureRepo extends JpaRepository<Facture, Long> {
    Optional<Facture> findByFactureCode(String factureCode);
}
