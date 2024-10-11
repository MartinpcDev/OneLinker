package com.martin.projects.onelinker.controller;

import com.martin.projects.onelinker.dto.request.LoginRequest;
import com.martin.projects.onelinker.dto.request.RegisterRequest;
import com.martin.projects.onelinker.dto.response.AuthResponse;
import com.martin.projects.onelinker.service.UserService;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest request) {
    userService.register(request);
    Map<String, String> response = new HashMap<>();
    response.put("message", "Registrado Correctamente");
    return ResponseEntity.ok(response);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
    return ResponseEntity.ok(userService.login(request));
  }
}
