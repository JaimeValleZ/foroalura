package com.aluralatam.foro.service.impl;

import com.aluralatam.foro.dto.DatosActualizacionTopico;
import com.aluralatam.foro.dto.DatosDetalleTopico;
import com.aluralatam.foro.dto.DatosListarTopicos;
import com.aluralatam.foro.dto.DatosRegistroTopico;
import com.aluralatam.foro.entity.Estado;
import com.aluralatam.foro.entity.Topico;
import com.aluralatam.foro.repository.ICursoRepository;
import com.aluralatam.foro.repository.ITopicoRepository;
import com.aluralatam.foro.repository.IUsuarioRepository;
import com.aluralatam.foro.service.ITopicoService;
import com.aluralatam.foro.validaciones.ITopicoValidaciones;
import com.aluralatam.foro.validaciones.ValidarUsuarioInactivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicoServiceImpl implements ITopicoService {

    @Autowired
    private ITopicoRepository topicoRepository;

    @Autowired
    private ICursoRepository cursoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private ValidarUsuarioInactivo validacion;

    @Override
    public DatosDetalleTopico crearTopico(DatosRegistroTopico datos) {
        var cursoId = datos.cursoId();
        var curso = cursoRepository.findById(cursoId).orElseThrow(() -> new IllegalArgumentException("No se encontró el curso con id: " + cursoId));
        var usuario = usuarioRepository.findById(datos.usuarioId()).orElseThrow(() -> new IllegalArgumentException("No se encontró el usuario"));
        validacion.validar(usuario.getId());
        var topico = new Topico(null, datos.titulo(), datos.mensaje(), LocalDateTime.now(),
                    Estado.SIN_RESPUESTA, usuario, curso, List.of());
        topicoRepository.save(topico);
        return new DatosDetalleTopico(topico);
    }

    @Override
    public Page<DatosListarTopicos> listarTopicos(Pageable pageable) {
        var topicos = topicoRepository.findAll(pageable).map(DatosListarTopicos::new);
        return topicos;
    }

    @Override
    public Page<DatosListarTopicos> listarTopicosPorCurso(String curso, Pageable pageable) {
        var topicos = topicoRepository.findByCurso(curso, pageable).map(DatosListarTopicos::new);
        return topicos;
    }

    @Override
    public DatosDetalleTopico buscarPorId(Long id) {
        var topico = topicoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No se encontró el tópico con id: " + id));
        return new DatosDetalleTopico(topico);
    }

    @Override
    public DatosDetalleTopico actualizarTopico(Long id, DatosActualizacionTopico datos) {
        var topico = topicoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No se encontró el tópico con id: " + id));
        topico.actualizarTopico(datos);
        if(datos.cursoId() != null){
            var cursoId = datos.cursoId();
            var curso = cursoRepository.findById(cursoId).orElseThrow(() -> new IllegalArgumentException("No se encontró el curso con id: " + cursoId));
            topico.setCurso(curso);
        }

        return new DatosDetalleTopico(topico);
    }

    @Override
    public void borrarPorId(Long id) {
         topicoRepository.deleteById(id);
    }

}