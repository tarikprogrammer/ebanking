package com.botola.clientapi.service;


import com.botola.clientapi.dto.TemporaryCardDto;
import com.botola.clientapi.entities.Account;
import com.botola.clientapi.entities.Client;
import com.botola.clientapi.entities.TemporaryCard;
import com.botola.clientapi.openfeigns.AgentVerifyEmail;
import com.botola.clientapi.repositories.AccountRepository;
import com.botola.clientapi.repositories.ClientRepository;
import com.botola.clientapi.repositories.OtpRepository;
import com.botola.clientapi.repositories.TempraryCardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemporaryCardServiceImp implements TemporaryCardService {
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;
    private final TempraryCardRepository tempraryCardRepository;
    private final JavaMailSender mailSender;


    @Override
    public boolean addTemporaryCard(TemporaryCard temporaryCard,String email) {
        Client search = clientRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException("client not found "));

        if(search.getAccount().getId() !=null){
            Account account = search.getAccount();
            if(account.getBalance() >=temporaryCard.getAmount()){
                account.setBalance(account.getBalance()-temporaryCard.getAmount());
                temporaryCard.setClient(search);
                tempraryCardRepository.save(temporaryCard);
                accountRepository.save(account);
                return true;
            }
        }

        return false;
    }
}
