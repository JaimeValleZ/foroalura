package com.aluralatam.foro.service.impl;

import com.aluralatam.foro.dto.DatosDetalleCurso;
import com.aluralatam.foro.dto.DatosRegistroCurso;
import com.aluralatam.foro.entity.Curso;
import com.aluralatam.foro.repository.ICursoRepository;
import com.aluralatam.foro.service.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoServiceImpl implements ICursoService {

    @Autowired
    private ICursoRepository cursoRepository;

    @Override
    public DatosDetalleCurso crearCurso(DatosRegistroCurso datos) {
        var curso = new Curso(null, datos.nombre(), datos.categoria());
        cursoRepository.save(curso);
        return new DatosDetalleCurso(curso);
    }
}
