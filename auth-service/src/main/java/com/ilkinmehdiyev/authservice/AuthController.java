package com.ilkinmehdiyev.authservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @GetMapping("/introspect")
    public ResponseEntity<Boolean> hasAccess(){
        log.info("Starting to AUTH process...");
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
