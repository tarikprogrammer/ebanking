package com.botola.apicmi.openfeigns;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
