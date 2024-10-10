package com.martin.projects.onelinker.dto.request;

public record ChangePasswordRequest(
    String currentPassword,
    String newPassword
) {

}
