package com.botola.apicmi.repositories;

import com.botola.apicmi.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
}
