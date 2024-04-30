package com.example.authservice.service.impl;

import com.example.authservice.config.Role;
import com.example.authservice.dto.*;
import com.example.authservice.entity.User;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.service.AuthenticationService;
import com.example.authservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponseDTO signIn(SignInRequestDTO request) {
        System.out.println(request.getUsername());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        AuthUserDTO user = userRepository.findByUsername(request.getUsername()).map(AuthUserDTO::new).orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        AuthenticationResponseDTO authenticationResponseDTO = new AuthenticationResponseDTO();
        authenticationResponseDTO.setToken(jwt);
        authenticationResponseDTO.setRefreshToken(refreshToken);
        return authenticationResponseDTO;
    }

    @Override
    public User signUp(SignUpRequestDTO request) {
        User user = new User();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAge(request.getAge());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
        return userRepository.save(user);
    }

    public AuthenticationResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO){
        String username = jwtService.extractUserName(refreshTokenRequestDTO.getToken());
        AuthUserDTO user = userRepository.findByUsername(username).map(AuthUserDTO::new).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequestDTO.getToken(),user)){
            var jwt = jwtService.generateToken(user);
            AuthenticationResponseDTO authenticationResponseDTO = new AuthenticationResponseDTO();
            authenticationResponseDTO.setToken(jwt);
            authenticationResponseDTO.setRefreshToken(refreshTokenRequestDTO.getToken());
            return authenticationResponseDTO;
        }
        return null;
    }
}