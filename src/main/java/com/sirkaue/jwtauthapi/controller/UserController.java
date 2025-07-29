package com.sirkaue.jwtauthapi.controller;

import com.sirkaue.jwtauthapi.domain.user.model.User;
import com.sirkaue.jwtauthapi.domain.user.service.UserService;
import com.sirkaue.jwtauthapi.dto.response.UserProfileResponse;
import com.sirkaue.jwtauthapi.mapper.UserMapper;
import com.sirkaue.jwtauthapi.security.authetication.AuthenticatedUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final AuthenticatedUserService authUserService;
    private final UserMapper userMapper;

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getMe() {
        Long userId = authUserService.getAuthenticatedUserId();
        User user = service.getById(userId);
        UserProfileResponse response = userMapper.toUserProfileResponse(user);
        return ResponseEntity.ok(response);
    }
}
