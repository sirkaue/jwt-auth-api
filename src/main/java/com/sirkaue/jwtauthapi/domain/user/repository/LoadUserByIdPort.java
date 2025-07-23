package com.sirkaue.jwtauthapi.domain.user.repository;

import org.springframework.security.core.userdetails.UserDetails;

public interface LoadUserByIdPort<ID> {

    UserDetails loadUserById(ID id);
}
