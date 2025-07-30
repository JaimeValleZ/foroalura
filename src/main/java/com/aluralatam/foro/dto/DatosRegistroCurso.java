package com.aluralatam.foro.dto;

import com.aluralatam.foro.entity.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroCurso(
        @NotBlank String nombre,
        @NotNull Categoria categoria
) {
}
