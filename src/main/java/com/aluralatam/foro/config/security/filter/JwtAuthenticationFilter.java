package com.aluralatam.foro.config.security.filter;

import com.aluralatam.foro.entity.Usuario;
import com.aluralatam.foro.repository.IUsuarioRepository;
import com.aluralatam.foro.service.impl.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //1. Obtener el Header del jwt
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ") ){
            filterChain.doFilter(request, response);
            return;
        }
        //2. Obtener el jwt desde header
        String jwt = authHeader.substring(7);

        //3. Obtener el username desde el jwt
        String username = jwtService.extractUsername(jwt);

        //4. Sertear objeto Authenticationd entro del SecurityContext
        Usuario user = usuarioRepository.findByUsername(username).get();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        //5. Ejecutar el resto de filtros
        filterChain.doFilter(request, response);
    }
}
