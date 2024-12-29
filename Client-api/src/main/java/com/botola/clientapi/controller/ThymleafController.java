package com.botola.clientapi.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymleafController {


    @GetMapping("/index")
    public String index() {
        return "sendLinkPassword";
    }
}
