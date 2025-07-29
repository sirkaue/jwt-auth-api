package com.sirkaue.jwtauthapi.security.jwt;

import com.sirkaue.jwtauthapi.domain.user.model.User;
import com.sirkaue.jwtauthapi.dto.TokenWithExpiration;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    private final String secretKey;
    private final long accessTokenExpiration;
    private final long refreshTokenExpiration;

    public JwtService(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.expiration.access}") long accessTokenExpiration,
            @Value("${jwt.expiration.refresh}") long refreshTokenExpiration
    ) {
        this.secretKey = secretKey;
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
    }

    public TokenWithExpiration generateAccessToken(User user) {
        return buildToken(user, accessTokenExpiration);
    }

    public TokenWithExpiration generateRefreshToken(User user) {
        return buildToken(user, refreshTokenExpiration);
    }

    private TokenWithExpiration buildToken(User user, long expirationMillis) {
        Instant now = Instant.now();
        Date issuedAt = Date.from(now);
        Date expiration = Date.from(now.plusMillis(expirationMillis));

        String sub = user.getId().toString();
        String token = Jwts.builder()
                .issuer("jwt-auth-api")
                .subject(sub)
                .claim("aud", "jwt-auth-client")
                .claim("name", user.getName())
                .claim("email", user.getEmail())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(getSignInKey())
                .compact();

        Instant expiresAt = now.plusMillis(expirationMillis);
        return new TokenWithExpiration(token, expiresAt);
    }

//    public String extractUsername(String token) {
//        return getJwtParser()
//                .parseSignedClaims(token)
//                .getPayload()
//                .getSubject();
//    }

    public Long extractUserId(String token) {
        String subject = getJwtParser()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        return Long.parseLong(subject);
    }

    public boolean isTokenValid(String token) {
        try {
            getJwtParser().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private JwtParser getJwtParser() {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build();
    }

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}
