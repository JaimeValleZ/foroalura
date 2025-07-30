package com.aluralatam.foro.service.impl;

import com.aluralatam.foro.dto.DatosDetalleUsuario;
import com.aluralatam.foro.dto.DatosListarUsuarios;
import com.aluralatam.foro.dto.DatosModificarUsuarios;
import com.aluralatam.foro.dto.DatosRegistroUsuario;
import com.aluralatam.foro.entity.Usuario;
import com.aluralatam.foro.repository.IUsuarioRepository;
import com.aluralatam.foro.service.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public DatosDetalleUsuario guardarUsuario(@Valid DatosRegistroUsuario datos) {
        var contrasena = datos.password();
        var usuario = new Usuario(null, datos.username(), passwordEncoder.encode(contrasena), datos.role(), true);
        usuarioRepository.save(usuario);
        return new DatosDetalleUsuario(usuario);
    }

    @Override
    public List<DatosListarUsuarios> listarUsuarios() {
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        return listaUsuarios.stream().map(DatosListarUsuarios::new).toList();
    }

    @Override
    public DatosDetalleUsuario actualizarUsuario(Long id, DatosModificarUsuarios datos) {
        var usuario = usuarioRepository.findById(id).orElseThrow();
        var contrasena = passwordEncoder.encode(datos.password());
        DatosModificarUsuarios usuarioEnviar = new DatosModificarUsuarios(contrasena);
        usuario.actualizarUsuario(usuarioEnviar);
        return new DatosDetalleUsuario(usuario);
    }

    @Override
    public void bloquearUsuario(Long id) {
        var usuario = usuarioRepository.findById(id).orElseThrow();
        usuario.desactivarUsuario();
    }
}
