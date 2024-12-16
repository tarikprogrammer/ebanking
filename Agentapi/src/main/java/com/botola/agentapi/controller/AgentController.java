package com.botola.agentapi.controller;



import com.botola.agentapi.dto.AgentDto;
import com.botola.agentapi.services.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agent-api")
@CrossOrigin("*")
public class AgentController {

    private final AgentService agentService;
    @PostMapping("/login")
    public ResponseEntity<AgentDto>login(@RequestBody Map<String,String>json){
        return ResponseEntity.ok(agentService.login(json.get("email"), json.get("password")));
    }

    @PostMapping("/sendLink")
    public void sendLink(@RequestBody Map<String,String>json){
        agentService.sendOTPtoResetPassword(json.get("email"));

    }

    @PostMapping("/sendOtp")
    public ResponseEntity<Boolean>sendOtp(@RequestBody Map<String,String>json){
        return ResponseEntity.ok(agentService.verfiyOtp(Integer.parseInt(json.get("otp")),json.get("email")));
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<Boolean>updatePassword(@RequestBody Map<String,String>json){
        return ResponseEntity.ok(agentService.updatePassword(json.get("password"),json.get("email")));
    }

    @GetMapping("/agent/{email}")
    public ResponseEntity<Boolean>getAgent(@PathVariable String email){
        return ResponseEntity.ok(agentService.verifyEmail(email));
    }
}
