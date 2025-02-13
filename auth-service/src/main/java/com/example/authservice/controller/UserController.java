package com.example.authservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@RestController
public class UserController {
    @GetMapping
    public ResponseEntity<String> sayHello(){
        System.out.println("hi user");
        return ResponseEntity.ok("hi user, im auth service");
    }
}