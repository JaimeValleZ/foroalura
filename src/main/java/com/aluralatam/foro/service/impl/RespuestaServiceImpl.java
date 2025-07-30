package com.aluralatam.foro.service.impl;

import com.aluralatam.foro.dto.DatosActualizacionRespuesta;
import com.aluralatam.foro.dto.DatosDetalleRespuesta;
import com.aluralatam.foro.dto.DatosRegistroRespuesta;
import com.aluralatam.foro.entity.Estado;
import com.aluralatam.foro.entity.Respuesta;
import com.aluralatam.foro.repository.IRespuestaRepository;
import com.aluralatam.foro.repository.ITopicoRepository;
import com.aluralatam.foro.repository.IUsuarioRepository;
import com.aluralatam.foro.service.IRespuestaService;
import com.aluralatam.foro.validaciones.ITopicoValidaciones;
import com.aluralatam.foro.validaciones.ValidarUsuarioInactivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RespuestaServiceImpl implements IRespuestaService {

    @Autowired
    private IRespuestaRepository respuestaRepository;

    @Autowired
    private ITopicoRepository topicoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private ValidarUsuarioInactivo validacion;

    @Override
    public DatosDetalleRespuesta crearRespuesta(DatosRegistroRespuesta datos) {
        var topico = topicoRepository.findById(datos.topicoId()).orElseThrow(() -> new IllegalArgumentException("No se encontró el topico"));
        var usuario = usuarioRepository.findById(datos.usuarioId()).orElseThrow(() -> new IllegalArgumentException("No se encontró el usuario"));
        validacion.validar(usuario.getId());
        var respuesta = new Respuesta(null, datos.mensaje(), topico, LocalDateTime.now(), usuario, false);
        topico.setRespuestas(List.of(respuesta));
        topico.setEstado(Estado.SIN_SOLUCION);
        respuestaRepository.save(respuesta);
        return new DatosDetalleRespuesta(respuesta);
    }

    @Override
    public DatosDetalleRespuesta actualizarRespuesta(Long id, DatosActualizacionRespuesta datos) {
        var respuesta = respuestaRepository.findById(id).orElseThrow();
        var topicoId = respuesta.getTopico().getId();
        var topico = topicoRepository.findById(topicoId).orElseThrow();
        respuesta.actualizarRespuesta(datos);
        if(respuesta.isSolucion()){
            topico.setEstado(Estado.SOLUCIONADO);
        }
        return new DatosDetalleRespuesta(respuesta);
    }
}
