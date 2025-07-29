package com.sirkaue.jwtauthapi.domain.user.service;

import com.sirkaue.jwtauthapi.domain.user.exception.UserNotFoundException;
import com.sirkaue.jwtauthapi.domain.user.model.User;
import com.sirkaue.jwtauthapi.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
