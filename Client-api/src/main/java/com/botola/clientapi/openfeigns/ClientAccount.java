package com.botola.clientapi.openfeigns;

import com.botola.clientapi.dto.AccountDto;
import com.botola.clientapi.dto.TempraryCardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "api-cmi")
public interface ClientAccount {


    @GetMapping("/api/accounts/getClientID/{clientId}")
    public List<AccountDto> getAccounts(@PathVariable("clientId") Long clientId);


    @GetMapping("/api/accounts/temporaryCard/{email}")
    public List<TempraryCardDto> getTemporaryCards(@PathVariable("email") String email);



}
