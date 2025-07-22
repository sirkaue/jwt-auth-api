package com.sirkaue.jwtauthapi.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Getter
@Configuration
@ConfigurationProperties(prefix = "app.cors")
public class CorsProperties {

    private final List<String> allowedOrigins = new ArrayList<>();
    private final List<String> allowedMethods = new ArrayList<>();
}
