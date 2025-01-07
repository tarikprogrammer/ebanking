package com.botola.apicmi.openfeigns;



import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/*
@FeignClient(name = "Client-api")
public interface ClientConn {

    @GetMapping("/client-api/client/get/{email}")
    public boolean getClientByemail(@PathVariable("email") String email);

    @GetMapping("/client-api/client/getClient/{email}")
    public Long getClient(@PathVariable("email") String email);


    @GetMapping("/client-api/client/getclientPhone/{email}")
    public String getClientPhone(@PathVariable("email") String email);

    @GetMapping("client-api/client/getclientName/{email}")
    public String getClientName(@PathVariable("email") String email);

    @GetMapping("client-api/client/getclientById/{id}")
    public String getClientById(@PathVariable Long id);
}
*/

@Component
public class ClientConn {
    private RestTemplate restTemplate = new RestTemplate();

    public boolean getClientByemail(@PathVariable("email") String email){
        String url = "http://3.84.236.79:8084/client-api/client/get/"+email;

        return restTemplate.getForObject(url, Boolean.class);
    }
    public Long getClient(@PathVariable("email") String email){
        String url = "http://3.84.236.79:8084/client-api/client/getClient/"+email;
        return restTemplate.getForObject(url, Long.class);
    }

    public String getClientPhone(@PathVariable("email") String email){
        String url = "http://3.84.236.79:8084/client-api/client/getclientPhone/"+email;
        return restTemplate.getForObject(url, String.class);
    }
    public String getClientName(@PathVariable("email") String email){
        String url = "http://3.84.236.79:8084/client-api/client/getclientName/"+email;
        return restTemplate.getForObject(url, String.class);
    }
    public String getClientById(@PathVariable Long id){
        String url = "http://3.84.236.79:8084/client-api/client/getclientById/"+id;
        return restTemplate.getForObject(url, String.class);
    }





}


