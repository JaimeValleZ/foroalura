package com.aluralatam.foro.dto;

import com.aluralatam.foro.entity.Estado;


import java.time.LocalDateTime;

public record DatosActualizacionTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Estado estado,
        Long cursoId,
        Long respuestaId
) {
}
