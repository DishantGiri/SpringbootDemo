package com.example.nobs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity

public class SecurityConfiguration {
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        UserDetails admin = org.springframework.security.core.userdetails.User
//                .withUsername("admin")
//                .authorities("SPECIAL", "BASIC")
//                .roles("superuser")
//                .password(encoder.encode("1"))
//
//                .build();
//        UserDetails user = org.springframework.security.core.userdetails.User
//                .withUsername("user")
//                .password(encoder.encode("2"))
//                .authorities("BASIC")
//                .roles("basicuser")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/createNewUser").permitAll();
                    auth.anyRequest().permitAll();
        })
                .httpBasic(httpBasic -> {})
                .build();

    }
}
