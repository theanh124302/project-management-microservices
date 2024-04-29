package com.example.authservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@RestController
public class AdminController {
    @GetMapping
    public ResponseEntity<String> sayHello(){
        System.out.println("hi admin");
        return ResponseEntity.ok("hi admin, im auth service");
    }
}
