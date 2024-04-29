package com.example.authservice.controller;

import com.example.authservice.dto.AuthenticationResponseDTO;
import com.example.authservice.dto.RefreshTokenRequestDTO;
import com.example.authservice.dto.SignInRequestDTO;
import com.example.authservice.dto.SignUpRequestDTO;
import com.example.authservice.entity.User;
import com.example.authservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequestDTO signUpRequest){
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponseDTO> signIn(@RequestBody SignInRequestDTO signInRequest){
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponseDTO> refresh(@RequestBody RefreshTokenRequestDTO refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}

