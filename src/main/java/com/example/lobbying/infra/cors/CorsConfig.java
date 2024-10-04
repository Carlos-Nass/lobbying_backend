package com.example.lobbying.infra.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")  // Origem do frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")  // Permitir todos os headers
                .allowCredentials(true);  // Permitir credenciais como cookies, etc.
    }

}
