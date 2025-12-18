package com.example.nobs.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class SecurityController {
    @GetMapping("/open")
    public String open(){
        return "Open endpoint";
    }
    @GetMapping("/closed")
    public String closed(){
        return "Closed endpoint";
    }
    @PreAuthorize("hasRole('superuser')")
    @GetMapping("/special")
    public String special(){
        return "Special endpoint";
    }
    @PreAuthorize("hasRole('superuser') or hasRole('basicuser')")
    @GetMapping("/basic")
    public String basic(){
        return "basic endpoint";
    }
}
