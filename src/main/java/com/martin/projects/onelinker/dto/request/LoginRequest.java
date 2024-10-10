package com.martin.projects.onelinker.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank(message = "El Email no puede ir vacio.")
    @Email(message = "El email debe tener el formato email permitido")
    String email,
    
    @NotBlank(message = "El password no puede ir vacio.")
    String password
) {

}
