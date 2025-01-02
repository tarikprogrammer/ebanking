package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Creditor;

@Repository
public interface CreditorRepository extends JpaRepository<Creditor, Long>{

}
