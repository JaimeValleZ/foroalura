package com.aluralatam.foro.service.impl;

import com.aluralatam.foro.dto.AuthenticationRequest;
import com.aluralatam.foro.dto.AuthenticationResponse;
import com.aluralatam.foro.entity.Usuario;
import com.aluralatam.foro.repository.IUsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse login(@Valid AuthenticationRequest authRequest) {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authRequest.username(), authRequest.password());

        authenticationManager.authenticate(authToken);

        Usuario user = usuarioRepository.findByUsername(authRequest.username()).get();

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        return new AuthenticationResponse(jwt);
    }

    private Map<String, Object> generateExtraClaims(Usuario user) {

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getUsername());
        extraClaims.put("role", user.getRole().name());
        extraClaims.put("permissions", user.getAuthorities());

        return extraClaims;
    }
}
