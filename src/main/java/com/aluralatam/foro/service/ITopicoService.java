package com.aluralatam.foro.service;

import com.aluralatam.foro.dto.DatosActualizacionTopico;
import com.aluralatam.foro.dto.DatosDetalleTopico;
import com.aluralatam.foro.dto.DatosListarTopicos;
import com.aluralatam.foro.dto.DatosRegistroTopico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITopicoService {

    public DatosDetalleTopico crearTopico(DatosRegistroTopico datos);
    public Page<DatosListarTopicos> listarTopicos(Pageable pageable);
    public Page<DatosListarTopicos> listarTopicosPorCurso(String curso, Pageable pageable);
    public DatosDetalleTopico buscarPorId(Long id);
    public DatosDetalleTopico actualizarTopico(Long id, DatosActualizacionTopico datos);
    public void borrarPorId(Long id);
}
