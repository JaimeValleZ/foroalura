package com.aluralatam.foro.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        LocalDateTime fechaCreacion,
        @NotNull Long usuarioId,
        @NotNull Long cursoId
        ) {
}
