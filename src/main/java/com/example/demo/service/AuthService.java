package com.example.demo.service;

import com.example.demo.model.Authority;
import com.example.demo.model.UserTokenState;
import com.example.demo.security.auth.JwtAuthenticationRequest;

import java.util.Set;

public interface AuthService {

    Set<Authority> findById(Long id);
    Set<Authority> findByName(String name);
    UserTokenState login(JwtAuthenticationRequest jwtAuthenticationRequest);

}
