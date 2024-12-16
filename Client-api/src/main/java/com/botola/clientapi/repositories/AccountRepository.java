package com.botola.clientapi.repositories;

import com.botola.clientapi.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account,Long> {
}
