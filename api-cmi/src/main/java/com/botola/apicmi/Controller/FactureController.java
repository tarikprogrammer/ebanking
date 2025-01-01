package com.botola.apicmi.Controller;

import com.botola.apicmi.Service.FactureService;
import com.botola.apicmi.dto.FactureDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factures")
public class FactureController {

    @Autowired
    private FactureService factureService;

    // Point de terminaison pour payer la facture
    @PostMapping("/payer/{accountId}/{factureId}")
    public ResponseEntity<String> payerFacture(@PathVariable Long accountId, @PathVariable Long factureId) {
        boolean paiementEffectue = factureService.verifierSoldeEtPayerFacture(accountId, factureId);
        if (paiementEffectue) {
            return ResponseEntity.ok("Paiement effectué avec succès.");
        } else {
            return ResponseEntity.badRequest().body("Le paiement a échoué. Vérifiez le solde ou l'état de la facture.");
        }
    }

    // Point de terminaison pour créer une facture
    @PostMapping("/creer/{accountId}")
    public ResponseEntity<FactureDTO> creerFacture(@PathVariable Long accountId, @RequestBody double montant) {
        FactureDTO factureDTO = factureService.creerFacture(accountId, montant);
        if (factureDTO != null) {
            return ResponseEntity.ok(factureDTO);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Point de terminaison pour lister les factures d'un compte
    @GetMapping("/lister/{accountId}")
    public ResponseEntity<List<FactureDTO>> listerFactures(@PathVariable Long accountId) {
        List<FactureDTO> facturesDTO = factureService.listerFactures(accountId);
        if (!facturesDTO.isEmpty()) {
            return ResponseEntity.ok(facturesDTO);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}

