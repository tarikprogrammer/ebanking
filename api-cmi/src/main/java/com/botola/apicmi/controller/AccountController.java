package com.botola.apicmi.controller;


import com.botola.apicmi.dto.*;
import com.botola.apicmi.entities.TemporaryCrad;
import com.botola.apicmi.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/accounts")

@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final TempraryCardService tempraryCardService;

    private final PaiementRechargeService paiementRechargeService;

    private final FactureService factureService;

    private final DonationService donationService;

    private final TransactionService transactionService;


    @PostMapping("/openAccountByAgent/{email}")
    public ResponseEntity<?> openAccountByAgent(@PathVariable String email, @RequestBody AccountDto accountDto) {
        return ResponseEntity.ok(accountService.openAccountByAgent(accountDto,email));
    }



    @GetMapping("/getClientID/{clientId}")
    public ResponseEntity<?> getAccountInfos(@PathVariable Long clientId) {
        return ResponseEntity.ok(accountService.accounts(clientId));
    }


    @PostMapping("/createTemporaryCard")
    public ResponseEntity<?> createTemporaryCard(@RequestBody TempraryCardDto tempraryCardDto) {

        return ResponseEntity.ok(tempraryCardService.createTempraryCard(tempraryCardDto));
    }

    @GetMapping("/temporaryCard/{email}")
    public ResponseEntity<?> getTemporaryCard(@PathVariable String email) {
        return ResponseEntity.ok(tempraryCardService.getTempraryCards(email));
    }

    @PostMapping("/payerRecharge")
    public ResponseEntity<?> payerRecharge(@RequestBody RechargeDto rechargeDto) {

        return ResponseEntity.ok(paiementRechargeService.acheterRecharge(rechargeDto));
    }


    @GetMapping("/factures/{code}")
    public ResponseEntity<?> getFactures(@PathVariable String code) {

        return ResponseEntity.ok(factureService.getFactureByCode(code));
    }



    @PostMapping("/payerFacture/{rib}")
    public ResponseEntity<?> payerFacture(@RequestBody FactureDto factureDto, @PathVariable String rib) {
        return ResponseEntity.ok(factureService.payerFacture(factureDto,rib));
    }


    @PostMapping("/donattion")

    public ResponseEntity<?> donation(@RequestBody DonationDto dto) {
        return ResponseEntity.ok(donationService.giveDonation(dto));
    }


    @PostMapping("/transaction")
    public ResponseEntity<?> transaction(@RequestBody TransactionRequestDto transactionRequestDto) {
        return ResponseEntity.ok(transactionService.createTransaction(transactionRequestDto));
    }






}
