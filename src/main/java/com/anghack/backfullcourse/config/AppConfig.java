package com.anghack.backfullcourse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.anghack.backfullcourse.exception.ResourceNotFoundException;
import com.anghack.backfullcourse.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final UserRepo userRepo;

    @Bean
    public UserDetailsService userDetails() {
        return username -> userRepo.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username " + username, 0));

    }

}
