package com.example.trabalho_tcc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()       // desabilita proteção CSRF (útil para API REST)
            .cors()                 // habilita CORS para requisições cross-origin
            .and()
            .authorizeHttpRequests()
                .anyRequest().permitAll();  // libera todas as requisições sem precisar de autenticação

        return http.build();
    }

    // Bean necessário para codificar senhas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
