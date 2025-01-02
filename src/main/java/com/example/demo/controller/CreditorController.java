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

import com.example.demo.model.Creditor;
import com.example.demo.service.CreditorService;

@RestController
@RequestMapping("/api/creditors")
public class CreditorController {
    private final CreditorService creditorService;

    public CreditorController(CreditorService creditorService) {
        this.creditorService = creditorService;
    }

    @GetMapping
    public List<Creditor> getAllCreditors() {
        System.out.println("GET request received for all creditors");
        return creditorService.getAllCreditors();
    }
    @PostMapping
    public Creditor createCreditor(@RequestBody Creditor creditor) {
        return creditorService.createCreditor(creditor);
    }

    @PutMapping("/{id}")
    public Creditor updateCreditor(@PathVariable Long id, @RequestBody Creditor creditor) {

        return creditorService.updateCreditor(id, creditor);
    }
    @DeleteMapping("/{id}")
    public void deleteCreditor(@PathVariable Long id) {
        creditorService.deleteCreditor(id);
    }
}
