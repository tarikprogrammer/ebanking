package com.botola.apicmi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiCmiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiCmiApplication.class, args);
    }

}
