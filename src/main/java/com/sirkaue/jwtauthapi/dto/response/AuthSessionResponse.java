package com.sirkaue.jwtauthapi.dto.response;

import java.time.Instant;

public record AuthSessionResponse(
        Instant accessTokenExpiresAt,
        String refreshToken,
        Instant refreshTokenExpiresAt
) {
}
