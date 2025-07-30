package com.aluralatam.foro.dto;

import com.aluralatam.foro.entity.Categoria;
import com.aluralatam.foro.entity.Curso;

public record DatosDetalleCurso(
        Long id,
        String nombre,
        Categoria categoria
) {
    public DatosDetalleCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
