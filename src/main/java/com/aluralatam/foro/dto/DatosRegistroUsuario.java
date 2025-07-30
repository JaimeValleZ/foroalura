package com.aluralatam.foro.dto;

import com.aluralatam.foro.util.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuario(
        @NotBlank @Email String username,
        @NotBlank String password,
        @NotNull Role role
) {
}
