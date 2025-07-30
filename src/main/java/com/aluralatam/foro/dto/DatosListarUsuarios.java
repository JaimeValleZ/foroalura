package com.aluralatam.foro.dto;

import com.aluralatam.foro.entity.Usuario;
import com.aluralatam.foro.util.Role;

public record DatosListarUsuarios(
        Long id,
        String username,
        String password,
        Role role,
        boolean enabled
) {
    public DatosListarUsuarios(Usuario usuario) {
        this(usuario.getId(), usuario.getUsername(), usuario.getPassword(), usuario.getRole(), usuario.isEnabled());
    }
}
