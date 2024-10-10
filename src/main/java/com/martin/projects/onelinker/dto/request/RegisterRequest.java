package com.martin.projects.onelinker.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank(message = "El username no puede ir vacio.")
    @Size(min = 8, max = 15, message = "El username debe tener entre 8 a 15 caracteres")
    String username,

    @NotBlank(message = "El Email no puede ir vacio.")
    @Email(message = "El email debe tener el formato email permitido")
    String email,

    @NotBlank(message = "El password no puede ir vacio.")
    @Size(min = 8, max = 15, message = "El username debe tener entre 8 a 15 caracteres")
    String password,

    @JsonProperty("repeat_password")
    @NotBlank(message = "El password no puede ir vacio.")
    String repeatPassword
) {

}
