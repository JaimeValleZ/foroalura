package com.aluralatam.foro.dto;

import com.aluralatam.foro.entity.Estado;
import com.aluralatam.foro.entity.Topico;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Estado estado,
        Long usuarioId,
        DatosDetalleCurso curso,
        List<DatosDetalleRespuesta> respuesta) {

    public DatosDetalleTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.getEstado(), topico.getUsuario().getId(), new DatosDetalleCurso(topico.getCurso()), topico.getRespuestas().stream().map(DatosDetalleRespuesta::new).toList());
    }
}
