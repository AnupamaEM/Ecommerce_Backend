package com.productproject.demo.RestController;

import com.google.firebase.auth.FirebaseAuth;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/store")
public class PasswordController {

    private final String FIREBASE_API_KEY = "AIzaSyDpWDPVm-v7QvRqIuxD-pnsp9gKgN8VYbw"; 

    @PostMapping("/forgot_password")
    public ResponseEntity<String> sendPasswordReset(@RequestParam("email") String email) {
        try {
            String url = "https://identitytoolkit.googleapis.com/v1/accounts:sendOobCode?key=" + FIREBASE_API_KEY;

            RestTemplate restTemplate = new RestTemplate();

            Map<String, String> body = new HashMap<>();
            body.put("requestType", "PASSWORD_RESET");
            body.put("email", email);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(body);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

            return ResponseEntity.ok("Password reset email sent successfully to " + email);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}

   
