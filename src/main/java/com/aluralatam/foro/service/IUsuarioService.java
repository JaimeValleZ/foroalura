package com.aluralatam.foro.service;

import com.aluralatam.foro.dto.DatosDetalleUsuario;
import com.aluralatam.foro.dto.DatosListarUsuarios;
import com.aluralatam.foro.dto.DatosModificarUsuarios;
import com.aluralatam.foro.dto.DatosRegistroUsuario;

import java.util.List;

public interface IUsuarioService {

    DatosDetalleUsuario guardarUsuario(DatosRegistroUsuario datos);
    List<DatosListarUsuarios> listarUsuarios();
    DatosDetalleUsuario actualizarUsuario(Long id, DatosModificarUsuarios datos);
    void bloquearUsuario(Long id);
}
