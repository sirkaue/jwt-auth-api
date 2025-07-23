package com.sirkaue.jwtauthapi.security.authetication.adapter;

import com.sirkaue.jwtauthapi.domain.user.repository.LoadUserByIdPort;
import com.sirkaue.jwtauthapi.domain.user.repository.UserRepository;
import com.sirkaue.jwtauthapi.security.authetication.UserAuthenticated;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoadUserByIdAdapter implements LoadUserByIdPort<Long> {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserById(Long id) {
        return repository.findById(id)
                .map(UserAuthenticated::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
