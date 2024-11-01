package com.productproject.demo.RestController;

import com.google.firebase.auth.FirebaseAuthException;
import com.productproject.demo.Service.FirebaseAuthService;
import com.productproject.demo.entity.SignInRequest;
import com.productproject.demo.entity.SignUpRequest;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/auth")
public class FirebaseAuthController {

    @Autowired
    private FirebaseAuthService firebaseAuthService;

    // Sign-up (register a new user)
    @PostMapping("/signup")
    public String signUpUser(@Valid @RequestBody SignUpRequest request) {
        try {
            String fid = firebaseAuthService.signUp(request);
            System.out.println("!!!keep fid,mey its needed for the next processes....");
            return "ur fid is here...!!!!! : " + fid;
        } catch (FirebaseAuthException e) {
            return "Error: " + e.getMessage();
        }
    }

    // Sign-in (login a user)
    @PostMapping("/signin")
    public String signInUser(@RequestBody SignInRequest request) {
        return firebaseAuthService.signIn(request);
    }

    // // forget password
    // @PostMapping("/forgot_password")
    // public String  forgotPassword(@RequestBody String email) {
    //     String response = firebaseAuthService.sendPasswordResetEmail(email);
    //     return "link for password reset sent to ur registered email";
    // }
}


