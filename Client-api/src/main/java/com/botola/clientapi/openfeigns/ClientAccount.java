package com.botola.clientapi.openfeigns;

import com.botola.clientapi.dto.AccountDto;
import com.botola.clientapi.dto.TempraryCardDto;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

/*
@FeignClient(name = "api-cmi")
public interface ClientAccount {


    @GetMapping("/api/accounts/getClientID/{clientId}")
    public List<AccountDto> getAccounts(@PathVariable("clientId") Long clientId);


    @GetMapping("/api/accounts/temporaryCard/{email}")
    public List<TempraryCardDto> getTemporaryCards(@PathVariable("email") String email);



}
*/

@Component

public class ClientAccount {
    private RestTemplate restTemplate = new RestTemplate();


    public List<AccountDto> getAccounts(@PathVariable("clientId") Long clientId) {
        String url = "http://3.84.236.79:8082/api/accounts/getClientID/"+clientId;
        ResponseEntity<List<AccountDto>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AccountDto>>() {}
        );

        return responseEntity.getBody();
    }

    public List<TempraryCardDto> getTemporaryCards(@PathVariable("email") String email){
        String url = "http://3.84.236.79:8082/api/accounts/temporaryCard/"+email;
        ResponseEntity<List<TempraryCardDto>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TempraryCardDto>>() {}
        );

        return responseEntity.getBody();
    }

}