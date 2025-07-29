package com.sirkaue.jwtauthapi.security.authetication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUserService {

    public Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAuthenticated userDetails = (UserAuthenticated) authentication.getPrincipal();
        return userDetails.getUser().getId();
    }
}
