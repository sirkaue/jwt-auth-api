package com.sirkaue.jwtauthapi.security.authetication;

import com.sirkaue.jwtauthapi.domain.user.model.User;
import com.sirkaue.jwtauthapi.domain.user.repository.UserRepository;
import com.sirkaue.jwtauthapi.dto.response.AuthResultResponse;
import com.sirkaue.jwtauthapi.dto.TokenWithExpiration;
import com.sirkaue.jwtauthapi.dto.request.LoginRequest;
import com.sirkaue.jwtauthapi.dto.request.RefreshTokenRequest;
import com.sirkaue.jwtauthapi.dto.request.RegisterRequest;
import com.sirkaue.jwtauthapi.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResultResponse register(RegisterRequest request) {
        if (!request.password().equals(request.confirmPassword())) {
            throw new IllegalArgumentException("Passwords don't match");
        }

        var user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();

        userRepository.save(user);

        return generateAuthResult(user);
    }

    public AuthResultResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        UserAuthenticated principal = (UserAuthenticated) authentication.getPrincipal();
        User user = principal.getUser();
        return generateAuthResult(user);
    }

    public AuthResultResponse refreshToken(RefreshTokenRequest request) {
        String refreshToken = request.refreshToken();

        if (!jwtService.isTokenValid(refreshToken)) {
            throw new IllegalArgumentException("Invalid refresh token");
        }

//        String userEmail = jwtService.extractUsername(refreshToken);
        Long userId = jwtService.extractUserId(refreshToken);
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return generateAuthResult(user);
    }

    private AuthResultResponse generateAuthResult(User user) {
        TokenWithExpiration accessToken = jwtService.generateAccessToken(user);
        TokenWithExpiration refreshToken = jwtService.generateRefreshToken(user);

        return new AuthResultResponse(
                accessToken.token(),
                accessToken.expiresAt(),
                refreshToken.token(),
                refreshToken.expiresAt()
        );
    }
}
