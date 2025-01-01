package com.botola.apicmi.Service;

import com.botola.apicmi.dto.FactureDTO;
import com.botola.apicmi.entities.Account;
import com.botola.apicmi.entities.Facture;
import com.botola.apicmi.Repository.AccountRepository;
import com.botola.apicmi.Repository.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FactureService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FactureRepository factureRepository;

    // Méthode existante pour vérifier le solde et payer la facture
    public boolean verifierSoldeEtPayerFacture(Long accountId, Long factureId) {
        // Récupérer le compte
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (!accountOptional.isPresent()) {
            return false; // Account non trouvé
        }

        Account account = accountOptional.get();

        // Vérifier la facture
        Optional<Facture> factureOptional = factureRepository.findById(factureId);
        if (!factureOptional.isPresent()) {
            return false; // Facture non trouvée
        }

        Facture facture = factureOptional.get();

        // Vérifier si la facture est déjà payée
        if (facture.isPayee()) {
            return false; // Facture déjà payée
        }

        // Vérifier si le solde est suffisant
        if (account.getSolde() < facture.getMontant()) {
            return false; // Solde insuffisant
        }

        // Effectuer le paiement
        account.setSolde(account.getSolde() - facture.getMontant());
        facture.setPayee(true);
        accountRepository.save(account);
        factureRepository.save(facture);

        return true; // Paiement effectué avec succès
    }

    // Méthode pour créer une facture
    public FactureDTO creerFacture(Long accountId, double montant) {
        // Récupérer le compte
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (!accountOptional.isPresent()) {
            return null; // Compte non trouvé
        }

        Account account = accountOptional.get();

        // Créer la nouvelle facture
        Facture facture = new Facture();
        facture.setMontant(montant);
        facture.setPayee(false); // Facture non payée à la création
        facture.setAccount(account); // Associer la facture au compte

        // Sauvegarder la facture
        Facture savedFacture = factureRepository.save(facture);

        // Retourner le DTO de la facture créée
        return new FactureDTO(savedFacture.getId(), savedFacture.getMontant(), savedFacture.isPayee());
    }

    // Méthode pour lister les factures par compte
    public List<FactureDTO> listerFactures(Long accountId) {
        // Récupérer le compte
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (!accountOptional.isPresent()) {
            return List.of(); // Compte non trouvé
        }

        Account account = accountOptional.get();

        // Récupérer les factures du compte et les convertir en DTOs
        List<Facture> factures = factureRepository.findByAccount(account);

        return factures.stream()
                .map(facture -> new FactureDTO(facture.getId(), facture.getMontant(), facture.isPayee()))
                .collect(Collectors.toList());
    }
}
