package com.ilkinmehdiyev.authservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @GetMapping
    public ResponseEntity<Boolean> hasAccess(){
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
