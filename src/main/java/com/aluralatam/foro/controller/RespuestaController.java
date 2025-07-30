package com.aluralatam.foro.controller;

import com.aluralatam.foro.dto.DatosActualizacionRespuesta;
import com.aluralatam.foro.dto.DatosRegistroRespuesta;
import com.aluralatam.foro.service.impl.RespuestaServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private RespuestaServiceImpl respuestaService;

    @PostMapping
    @Transactional
    public ResponseEntity crearRespuesta(@RequestBody @Valid DatosRegistroRespuesta datos, UriComponentsBuilder uriComponentsBuilder){
        var respuesta = respuestaService.crearRespuesta(datos);
        var uri = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(uri).body(respuesta);
    }

    @PutMapping("/modificar/{id}")
    @Transactional
    public ResponseEntity modificarRespuesta(@PathVariable Long id, @RequestBody DatosActualizacionRespuesta datos){
        var respuesta = respuestaService.actualizarRespuesta(id, datos);
        return ResponseEntity.ok(respuesta);
    }

}
