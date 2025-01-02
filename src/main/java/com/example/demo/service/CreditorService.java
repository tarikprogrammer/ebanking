package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Creditor;

import com.example.demo.repository.CreditorRepository;

@Service
public class CreditorService {

     private final CreditorRepository creditorRepository;

     public CreditorService(CreditorRepository creditorRepository) {
        this.creditorRepository = creditorRepository;
    }
     public List<Creditor> getAllCreditors(){
        if (creditorRepository == null) {
            System.out.println("CreditorRepository is null");
        } else {
            System.out.println("CreditorRepository is injected properly");
        }
        return creditorRepository.findAll();
     }

     public Creditor getCreditorById(Long id) {
        return creditorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Creditor not found"));
    }

    public Creditor createCreditor(Creditor creditor) {
        return creditorRepository.save(creditor);
    }

    public Creditor updateCreditor(Long id, Creditor updatedCreditor) {
        Creditor creditor = getCreditorById(id);
        creditor.setName(updatedCreditor.getName());
        creditor.setEmail(updatedCreditor.getEmail());
        creditor.setType(updatedCreditor.getType());
        creditor.setBalance(updatedCreditor.getBalance());
        return creditorRepository.save(creditor);
    }

    public void deleteCreditor(Long id) {
        creditorRepository.deleteById(id);
    }
}
