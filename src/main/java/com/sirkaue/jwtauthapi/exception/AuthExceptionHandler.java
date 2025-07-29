package com.sirkaue.jwtauthapi.exception;

import com.sirkaue.jwtauthapi.domain.user.exception.JwtExpiredException;
import com.sirkaue.jwtauthapi.domain.user.exception.JwtInvalidException;
import com.sirkaue.jwtauthapi.domain.user.exception.UserNotFoundException;
import com.sirkaue.jwtauthapi.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(UserNotFoundException ex, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse(request, HttpStatus.NOT_FOUND, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler({BadCredentialsException.class, UsernameNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleAuthErrors(HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse(request, HttpStatus.UNAUTHORIZED, new Exception("Invalid email or password"));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler({JwtExpiredException.class, JwtInvalidException.class})
    public ResponseEntity<ErrorResponse> handleJwtExceptions(RuntimeException ex, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse(request, HttpStatus.UNAUTHORIZED, ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}
