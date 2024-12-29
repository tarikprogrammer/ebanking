package com.botola.clientapi.repositories;



import com.botola.clientapi.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
    Optional<Client> findByEmailAndPassword(String email, String password);
    Optional<Client> findByPhone(String phone);
    Page<Client>findAll(Pageable pageable);  // size
}
