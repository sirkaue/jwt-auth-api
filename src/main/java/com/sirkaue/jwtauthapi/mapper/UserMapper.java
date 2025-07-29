package com.sirkaue.jwtauthapi.mapper;

import com.sirkaue.jwtauthapi.domain.user.model.User;
import com.sirkaue.jwtauthapi.dto.response.UserProfileResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserProfileResponse toUserProfileResponse(User user) {
        return new UserProfileResponse(user.getName(), user.getEmail());
    }
}
