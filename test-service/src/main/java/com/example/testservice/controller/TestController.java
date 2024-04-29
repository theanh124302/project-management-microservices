package com.example.testservice.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/")
public class TestController {
    @RequestMapping("/")
    public String get() {
        return "oke rồi";
    }
}