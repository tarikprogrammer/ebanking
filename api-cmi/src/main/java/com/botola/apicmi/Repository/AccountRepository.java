package com.botola.apicmi.Repository;

import com.botola.apicmi.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findById(long id);
}
