package com.aluralatam.foro.controller;

import com.aluralatam.foro.dto.DatosModificarUsuarios;
import com.aluralatam.foro.dto.DatosRegistroUsuario;
import com.aluralatam.foro.service.impl.UsuarioServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity guardarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario){
        var usuario = usuarioService.guardarUsuario(datosRegistroUsuario);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarUsuario(@PathVariable Long id, @RequestBody DatosModificarUsuarios datos){
        var usuario = usuarioService.actualizarUsuario(id, datos);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity listarUsuarios(){
        var usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("/desactivar/{id}")
    @Transactional
    public ResponseEntity desactivarUsuario(@PathVariable Long id){
        usuarioService.bloquearUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
