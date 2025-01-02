package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Creditor;
import com.example.demo.model.Debt;
import com.example.demo.repository.CreditorRepository;
import com.example.demo.repository.DebtRepository;
@Service
public class DebtService {
    private final DebtRepository debtRepository;
    private final CreditorRepository creditorRepository;

    public DebtService(DebtRepository debtRepository, CreditorRepository creditorRepository) {
        this.debtRepository = debtRepository;
        this.creditorRepository = creditorRepository;
         
    }
     public List<Debt> getAllDebts() {
        return debtRepository.findAll();
    }

    public Debt getDebtById(Long id) {
        return debtRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Debt not found"));
    }
     public Debt createDebt(Debt debt) {
        if (debt.getCreditorId() != null) {
            Creditor creditor = creditorRepository.findById(debt.getCreditorId())
                    .orElseThrow(() -> new RuntimeException("Creditor not found"));
            debt.setCreditor(creditor);
        }
        return debtRepository.save(debt);
    }
    public Debt updateDebt(Long id, Debt updatedDebt) {
        Debt debt = getDebtById(id);
        debt.setAmount(updatedDebt.getAmount());
        debt.setDueDate(updatedDebt.getDueDate());
        return debtRepository.save(debt);
    }

    public void deleteDebt(Long id) {
        debtRepository.deleteById(id);
    }
}
