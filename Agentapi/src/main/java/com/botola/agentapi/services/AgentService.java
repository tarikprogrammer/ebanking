package com.botola.agentapi.services;


import com.botola.agentapi.dto.AgentDto;

public interface AgentService {
    AgentDto login(String email, String password);
    void sendOTPtoResetPassword(String email);
    boolean verfiyOtp(int otp,String email);
    AgentDto updatePassword(String newPassword, String email);
    boolean verifyEmail(String email);
}
