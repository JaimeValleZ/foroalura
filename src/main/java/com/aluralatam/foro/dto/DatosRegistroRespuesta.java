package com.aluralatam.foro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroRespuesta(
        @NotBlank String mensaje,
        @NotNull Long topicoId,
        @NotNull Long usuarioId,
        LocalDateTime fechaCreacio
) {
}
