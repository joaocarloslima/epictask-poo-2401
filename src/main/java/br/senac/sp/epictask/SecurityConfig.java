package br.senac.sp.epictask;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain config(HttpSecurity http) throws Exception{

        return http
                .oauth2Login(Customizer.withDefaults())
                .csrf( csrf -> csrf.disable())
                .build();

    }
    
}
