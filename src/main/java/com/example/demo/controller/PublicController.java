package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class PublicController {
    @GetMapping("/test")
    public String testConnection() {
        return "Connection to the server is successful!";
    }
}
