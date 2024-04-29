package com.example.authservice.service;

import com.example.authservice.dto.AuthenticationResponseDTO;
import com.example.authservice.dto.RefreshTokenRequestDTO;
import com.example.authservice.dto.SignInRequestDTO;
import com.example.authservice.dto.SignUpRequestDTO;
import com.example.authservice.entity.User;

public interface AuthenticationService {
    AuthenticationResponseDTO signIn(SignInRequestDTO signInRequest);

    User signUp(SignUpRequestDTO signUpRequest);
    public AuthenticationResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequest);
}