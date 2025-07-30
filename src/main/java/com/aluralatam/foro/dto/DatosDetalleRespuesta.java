package com.aluralatam.foro.dto;

import com.aluralatam.foro.entity.Respuesta;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fechaCreacion,
        String tituloTopico,
        Long usuarioId,
        boolean solucion
) {
    public DatosDetalleRespuesta(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getFechaCreacion(), respuesta.getTopico().getTitulo(), respuesta.getUsuario().getId(), respuesta.isSolucion());
    }
}
