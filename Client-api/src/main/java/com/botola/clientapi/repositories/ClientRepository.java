package com.botola.clientapi.repositories;



import com.botola.clientapi.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
    Client findByEmailAndPassword(String email, String password);
    Client findByPhone(String phone);
    Page<Client>findAll(Pageable pageable);  // size
}
