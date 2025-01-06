package com.botola.apicmi.services;


import com.botola.apicmi.dto.TransactionRequestDto;
import com.botola.apicmi.entities.Account;
import com.botola.apicmi.entities.TemporaryCrad;
import com.botola.apicmi.entities.Transaction;
import com.botola.apicmi.enums.TransactionStatus;
import com.botola.apicmi.repositories.AccountRepo;
import com.botola.apicmi.repositories.TemporarycardRepo;
import com.botola.apicmi.repositories.TransactionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.time.LocalDateTime;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TemporarycardRepo temporarycardRepo;
    private final AccountRepo accountRepo;
    private final JavaMailSender mailSender;
    private final TransactionRepo transactionRepo;





    public boolean createTransaction(TransactionRequestDto request){

        Optional<Account> accountOptSender = accountRepo.findByIban(request.getRibSender());


        if (accountOptSender.isPresent()) {
            Account account = accountOptSender.get();
            double amountSender  = account.getSolde();
            if (amountSender>= request.getAmount()) {

                Optional<Account>  accountOptReceiver= accountRepo.findByIban(request.getRibReceiver());

                if (accountOptReceiver.isPresent()) {
                    Account accountReceiver = accountOptReceiver.get();
                    double amountReceiver  = accountReceiver.getSolde();
                    amountSender -=request.getAmount();;
                    amountReceiver += request.getAmount();
                    if(amountReceiver <=accountReceiver.getPlafond()){
                        accountReceiver.setSolde(amountReceiver);
                        accountRepo.save(accountReceiver);
                        account.setSolde(amountSender);
                        accountRepo.save(account);

                        Transaction transaction = Transaction.builder()
                                .ribSender(request.getRibSender())
                                .ribReceiver(request.getRibReceiver())
                                .amount(request.getAmount())
                                .transactionDate(LocalDateTime.now())
                                .transactionNumber(request.getRibReceiver().substring(0,5))
                                .currency(account.getDevice())
                                .status(TransactionStatus.SUCCESS)
                                .build();

                        transactionRepo.save(transaction);
                        return true;

                    }else {
                        return false;
                    }
                }
                else {
                    return false;
                }


            }else {
                return false;
            }
        }

        Optional<TemporaryCrad> temporaryCardOptSender = temporarycardRepo.findByIban(request.getRibSender());

        if (temporaryCardOptSender.isPresent()) {
            TemporaryCrad temporaryCardSender = temporaryCardOptSender.get();

            double soldeCardSender = temporaryCardSender.getSolde();

            if (soldeCardSender >= request.getAmount()) {
                Optional<TemporaryCrad> temporaryCardOptReceiver = temporarycardRepo.findByIban(request.getRibReceiver());

                if (temporaryCardOptReceiver.isPresent()) {
                    TemporaryCrad temporaryCardReceiver = temporaryCardOptReceiver.get();
                    double amountReceiver  = temporaryCardReceiver.getSolde();
                    amountReceiver += request.getAmount();
                    soldeCardSender -= request.getAmount();
                    temporaryCardSender.setSolde(soldeCardSender);
                    temporarycardRepo.save(temporaryCardSender);
                    temporaryCardReceiver.setSolde(amountReceiver);
                    temporarycardRepo.save(temporaryCardReceiver);


                    Transaction transaction = Transaction.builder()
                            .ribSender(request.getRibSender())
                            .ribReceiver(request.getRibReceiver())
                            .amount(request.getAmount())
                            .transactionDate(LocalDateTime.now())
                            .transactionNumber(request.getRibReceiver().substring(0,5))
                            .currency("NAN")
                            .status(TransactionStatus.SUCCESS)
                            .build();

                    transactionRepo.save(transaction);

                    return true;

                }else {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        return false;
    }



}
