package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Debt;
import com.example.demo.service.DebtService;

@RestController
@RequestMapping("/api/debts")
public class DebtController {
    private final DebtService debtService;

    public DebtController(DebtService debtService) {
        this.debtService = debtService;
    }

    @GetMapping
    public List<Debt> getAllDebts() {
        return debtService.getAllDebts();
    }
    @PostMapping
    public Debt createDebt(@RequestBody Debt debt) {
        return debtService.createDebt(debt);  // L'ID du créancier est inclus dans l'objet Debt
    }

    @PutMapping("/{id}")
    public Debt updateDebt(@PathVariable Long id, @RequestBody Debt debt) {
        return debtService.updateDebt(id, debt);
    }

    @DeleteMapping("/{id}")
    public void deleteDebt(@PathVariable Long id) {
        debtService.deleteDebt(id);
    }
}
