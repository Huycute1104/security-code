package com.example.securitycode.service;

public interface IEmailService {
    public String extractSecurityCode(String body);
}
