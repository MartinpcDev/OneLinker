package com.martin.projects.onelinker.service.impl;

import com.martin.projects.onelinker.dto.request.ChangePasswordRequest;
import com.martin.projects.onelinker.dto.request.LoginRequest;
import com.martin.projects.onelinker.dto.request.RegisterRequest;
import com.martin.projects.onelinker.dto.response.AuthResponse;
import com.martin.projects.onelinker.exceptions.BadAuthenticationException;
import com.martin.projects.onelinker.exceptions.NoConfirmatedPasswordException;
import com.martin.projects.onelinker.exceptions.ResourceNotFoundException;
import com.martin.projects.onelinker.persistence.entity.User;
import com.martin.projects.onelinker.persistence.repository.UserRepository;
import com.martin.projects.onelinker.service.JwtService;
import com.martin.projects.onelinker.service.UserService;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;


  @Override
  public User register(RegisterRequest request) {

    if (userRepository.findByEmailIgnoreCase(request.email()).isPresent()) {
      throw new IllegalStateException("Email ya registrado");
    }
    if (userRepository.findByUsernameIgnoreCase(request.username()).isPresent()) {
      throw new IllegalStateException("Username ya registrado");
    }
    if (!Objects.equals(request.password(), request.repeatPassword())) {
      throw new NoConfirmatedPasswordException("Passwords no coinciden");
    }

    User user = new User();
    user.setPassword(passwordEncoder.encode(request.password()));
    user.setUsername(request.username());
    user.setEmail(request.email());

    return userRepository.save(user);
  }

  @Override
  public AuthResponse login(LoginRequest request) {
    Optional<User> user = userRepository.findByEmailIgnoreCase(request.email());
    if (user.isEmpty()) {
      throw new BadAuthenticationException("Email no existe");
    }

    if (!passwordEncoder.matches(request.password(), user.get().getPassword())) {
      throw new BadAuthenticationException("Password no coinciden");
    }

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.email(), request.password())
    );

    final UserDetails userDetails = getByEmail(request.email());
    final String jwt = jwtService.generateToken(userDetails);

    return new AuthResponse("Logeado Correctamente", jwt);
  }

  @Override
  public User getByEmail(String email) {
    return userRepository.findByEmailIgnoreCase(email)
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
  }

  @Override
  public void changePassword(String email, ChangePasswordRequest request) {
    User user = getByEmail(email);
    if (!passwordEncoder.matches(request.currentPassword(), user.getPassword())) {
      throw new BadCredentialsException("Current password is incorrect");
    }
    user.setPassword(passwordEncoder.encode(request.newPassword()));
    userRepository.save(user);
  }
}
