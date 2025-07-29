package com.sirkaue.jwtauthapi.domain.user.exception;

public class JwtExpiredException extends RuntimeException {

    public JwtExpiredException(String message) {
        super(message);
    }
}
