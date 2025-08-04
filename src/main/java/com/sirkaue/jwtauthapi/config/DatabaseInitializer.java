package com.sirkaue.jwtauthapi.config;

import com.sirkaue.jwtauthapi.domain.user.model.User;
import com.sirkaue.jwtauthapi.domain.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            // Senha: "admin123" (hash bcrypt)
            if (userRepository.count() == 0) {
                userRepository.save(new User(
                        "Kauê Dev",
                        "kaue@example.com",
                        "$2a$12$QjKRXbAq5pNPyvmcm6M3s.WDs7TPnxHuasJgVh0GmFgZqxBqjfqca"
                ));
            }
        };
    }
}
