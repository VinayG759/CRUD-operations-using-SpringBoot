package com.college.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/signin").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/signin")      // 👈 YOUR PAGE
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/students", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/signin?logout")
            );

        return http.build();
    }
}
