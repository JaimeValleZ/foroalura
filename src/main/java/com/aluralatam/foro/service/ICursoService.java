package com.aluralatam.foro.service;

import com.aluralatam.foro.dto.DatosDetalleCurso;
import com.aluralatam.foro.dto.DatosRegistroCurso;

public interface ICursoService {

    public DatosDetalleCurso crearCurso(DatosRegistroCurso datos);
}
