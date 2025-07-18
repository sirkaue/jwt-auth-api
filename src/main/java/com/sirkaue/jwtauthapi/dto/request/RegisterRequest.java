package com.sirkaue.jwtauthapi.dto.request;

public record RegisterRequest(
        String name,
        String email,
        String password,
        String confirmPassword
) {
}
