package com.aluralatam.foro.service;

import com.aluralatam.foro.dto.DatosActualizacionRespuesta;
import com.aluralatam.foro.dto.DatosDetalleRespuesta;
import com.aluralatam.foro.dto.DatosRegistroRespuesta;

public interface IRespuestaService {

    DatosDetalleRespuesta crearRespuesta(DatosRegistroRespuesta datos);
    DatosDetalleRespuesta actualizarRespuesta(Long id, DatosActualizacionRespuesta datos);

}
