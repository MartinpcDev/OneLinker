package com.martin.projects.onelinker.service;

import com.martin.projects.onelinker.dto.request.ChangePasswordRequest;
import com.martin.projects.onelinker.dto.request.LoginRequest;
import com.martin.projects.onelinker.dto.request.RegisterRequest;
import com.martin.projects.onelinker.persistence.entity.User;

public interface UserService {

  User register(RegisterRequest request);

  User login(LoginRequest request);

  User getByEmail(String email);

  void changePassword(String email, ChangePasswordRequest request);
}
