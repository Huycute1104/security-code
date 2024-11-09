package com.example.securitycode.controller;

import com.example.securitycode.model.EmailData;
import com.example.securitycode.model.EmailResponse;
import com.example.securitycode.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmailController {
    // http://localhost:8080/swagger-ui.html
    @Autowired
    private IEmailService emailService;

    @PostMapping("/extract")
    public ResponseEntity<List<EmailResponse>> extractEmailAndCode(@RequestBody List<EmailData> emailDataList) {
        List<EmailResponse> responseList = new ArrayList<>();

        for (EmailData emailData : emailDataList) {
            String email = emailData.getTo();
            String securityCode = emailService.extractSecurityCode(emailData.getBody());

            if (email != null && securityCode != null) {
                responseList.add(new EmailResponse(email, securityCode));
            }
        }

        if (!responseList.isEmpty()) {
            return ResponseEntity.ok(responseList);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
