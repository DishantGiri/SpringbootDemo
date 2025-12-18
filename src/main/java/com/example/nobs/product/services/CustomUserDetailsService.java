package com.example.nobs.product.services;

import com.example.nobs.product.CustomUserRepo;
import com.example.nobs.product.model.CustomUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final CustomUserRepo customUserRepo;

    public CustomUserDetailsService(CustomUserRepo customUserRepo) {
        this.customUserRepo = customUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser customUser = customUserRepo.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return User.withUsername(customUser.getUsername())
                .password(customUser.getPassword())
                .authorities("USER")
                .build();
    }
}
