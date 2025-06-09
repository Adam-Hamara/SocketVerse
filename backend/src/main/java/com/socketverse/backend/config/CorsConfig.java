package com.socketverse.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")                    // Allow CORS on all paths
                .allowedOrigins("http://localhost:4200") // Allow specific origin(s)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
                .allowedHeaders("*")                   // Allow all headers
                .allowCredentials(true)                // Allow cookies/auth headers
                .maxAge(3600);                         // Cache preflight response for 1 hour
    }
}
