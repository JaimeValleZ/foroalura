package com.aluralatam.foro.controller;

import com.aluralatam.foro.dto.DatosRegistroCurso;
import com.aluralatam.foro.service.impl.CursoServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoServiceImpl cursoServiceImpl;

    @PostMapping
    @Transactional
    public ResponseEntity crearCurso(@RequestBody @Valid DatosRegistroCurso datos, UriComponentsBuilder uriComponentsBuilder){
        var datosCurso = cursoServiceImpl.crearCurso(datos);
        var uri = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(datosCurso.id()).toUri();
        return ResponseEntity.created(uri).body(datosCurso);
    }
}
