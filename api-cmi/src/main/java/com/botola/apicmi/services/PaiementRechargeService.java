package com.botola.apicmi.services;


import com.botola.apicmi.dto.RechargeDto;
import com.botola.apicmi.entities.Account;
import com.botola.apicmi.entities.TemporaryCrad;
import com.botola.apicmi.openfeigns.ClientConn;
import com.botola.apicmi.repositories.AccountRepo;
import com.botola.apicmi.repositories.TemporarycardRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaiementRechargeService {
    private final TemporarycardRepo temporarycardRepo;

    private final ClientConn clientConn;

    private final AccountRepo accountRepo;

    private final JavaMailSender mailSender;



    /*
    * montant
    * rib
    * */

    public boolean acheterRecharge(RechargeDto rechargeDto) {
        // Search in account
        Optional<Account> accountOpt = accountRepo.findByIban(rechargeDto.getRib());

        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            double solde = account.getSolde();
            if (solde >= rechargeDto.getMontant()) {
                solde -= rechargeDto.getMontant();
                account.setSolde(solde);
                accountRepo.save(account);
                return true;
            } else {
                return false; // Insufficient balance
            }
        }

        // If not found in Account, search in TemporaryCard
        Optional<TemporaryCrad> temporaryCardOpt = temporarycardRepo.findByIban(rechargeDto.getRib());

        if (temporaryCardOpt.isPresent()) {
            TemporaryCrad temporaryCard = temporaryCardOpt.get();
            double solde = temporaryCard.getSolde();
            if (solde >= rechargeDto.getMontant()) {
                solde -= rechargeDto.getMontant();
                temporaryCard.setSolde(solde);
                temporarycardRepo.save(temporaryCard);
                return true;
            } else {
                return false; // Insufficient balance
            }
        }

        // If neither account nor temporary card exists
        return false;
    }


}
