package com.botola.apicmi.repositories;

import com.botola.apicmi.entities.TemporaryCrad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TemporarycardRepo extends JpaRepository<TemporaryCrad,Long> {
    Optional<TemporaryCrad> findByCvv(String cvv);
    List<TemporaryCrad> findByEmail(String email);
    Optional<TemporaryCrad>findByIban(String iban);
}
