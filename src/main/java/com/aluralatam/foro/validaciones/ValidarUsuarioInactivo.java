package com.aluralatam.foro.validaciones;


import com.aluralatam.foro.exceptions.ValidationException;
import com.aluralatam.foro.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarUsuarioInactivo {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    public void validar(Long id) {
        boolean usuarioActivoONo = usuarioRepository.findActivoById(id);
        if(!usuarioActivoONo){
            throw new ValidationException("El usuario esta bloqueado");
        }
    }
}
