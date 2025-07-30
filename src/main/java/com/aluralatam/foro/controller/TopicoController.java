package com.aluralatam.foro.controller;

import com.aluralatam.foro.dto.DatosActualizacionTopico;
import com.aluralatam.foro.dto.DatosListarTopicos;
import com.aluralatam.foro.dto.DatosRegistroTopico;
import com.aluralatam.foro.service.impl.TopicoServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoServiceImpl topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity crearTopico(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder){
        var datosTopico = topicoService.crearTopico(datos);
        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(datosTopico.id()).toUri();
        return ResponseEntity.created(uri).body(datosTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListarTopicos>> listarTopicos(@PageableDefault(size = 10, sort = {"fechaCreacion"}, direction = Sort.Direction.DESC) Pageable pageable){
        var topicos = topicoService.listarTopicos(pageable);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/curso/{curso}")
    public ResponseEntity<Page<DatosListarTopicos>> listarTopicosPorCurso(@PathVariable String curso, @PageableDefault(size = 10, sort = {"fechaCreacion"}, direction = Sort.Direction.DESC) Pageable pageable){
        var topicos = topicoService.listarTopicosPorCurso(curso, pageable);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id){
        var topico = topicoService.buscarPorId(id);
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/modificar/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody DatosActualizacionTopico datos){
        var topico = topicoService.actualizarTopico(id, datos);
        return ResponseEntity.ok(topico);
    }

    @DeleteMapping("/borrar/{id}")
    @Transactional
    public ResponseEntity borrarPorId(@PathVariable Long id){
        topicoService.borrarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
