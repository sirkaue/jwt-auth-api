package com.sirkaue.jwtauthapi.dto;

import java.time.Instant;

public record TokenWithExpiration(String token, Instant expiresAt) {
}

