package com.sirkaue.jwtauthapi.dto.response;

import java.time.Instant;

public record AuthResultResponse(
        String accessToken,
        Instant accessTokenExpiresAt,
        String refreshToken,
        Instant refreshTokenExpiresAt
) {
}
