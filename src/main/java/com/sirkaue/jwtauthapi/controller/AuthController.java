package com.sirkaue.jwtauthapi.controller;

import com.sirkaue.jwtauthapi.dto.request.RegisterRequest;
import com.sirkaue.jwtauthapi.dto.response.AuthResultResponse;
import com.sirkaue.jwtauthapi.security.authetication.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResultResponse> register(@RequestBody RegisterRequest request) {
        AuthResultResponse result = authService.register(request);
        return ResponseEntity.ok().body(result);
    }
}
