package com.javaredo.fxconnectfour_backend.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javaredo.fxconnectfour_backend.user.AppUser;
import com.javaredo.fxconnectfour_backend.user.AppUserRepository;

@Service
public class AuthService {
    private final AppUserRepository repo;
    private final PasswordEncoder encoder;

    public AuthService(AppUserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public void register(String username, String rawPassword) {
        if (repo.existsByUsername(username)) throw new RuntimeException("Username taken");

        AppUser u = new AppUser();
        u.setUsername(username);
        u.setPassword(encoder.encode(rawPassword));
        u.getRoles().add("ROLE_USER");

        repo.save(u);
    }
}