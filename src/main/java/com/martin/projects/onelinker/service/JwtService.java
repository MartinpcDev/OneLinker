package com.martin.projects.onelinker.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

  String generateToken(UserDetails userDetails);

  Boolean validateToken(String token, UserDetails userDetails);

  String extractUsername(String token);
}
