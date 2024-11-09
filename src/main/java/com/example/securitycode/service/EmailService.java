package com.example.securitycode.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    @Override
    public String extractSecurityCode(String body) {
        String securityCodePrefix = "Security code: ";
        int startIndex = body.indexOf(securityCodePrefix);
        if (startIndex != -1) {
            startIndex += securityCodePrefix.length();
            int endIndex = body.indexOf("\n", startIndex);
            if (endIndex != -1) {
                return body.substring(startIndex, endIndex).trim();
            }
        }
        return null;
    }
}
