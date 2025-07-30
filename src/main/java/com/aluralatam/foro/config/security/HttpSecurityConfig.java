package com.aluralatam.foro.config.security;

import com.aluralatam.foro.config.security.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sessionMangConfig -> sessionMangConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authConfig -> {

                    authConfig.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/auth/publico").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST,"/usuarios/").permitAll();
                    authConfig.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll();


                    authConfig.requestMatchers("/topicos/**").hasAnyRole("ADMIN", "STUDENT", "TEACHER");
                    authConfig.requestMatchers("/cursos/**").hasAnyRole("ADMIN", "STUDENT", "TEACHER");
                    authConfig.requestMatchers("/respuestas/**").hasAnyRole("STUDENT", "TEACHER");
                    authConfig.requestMatchers("/usuarios/**").hasAnyRole("ADMIN");


                    authConfig.anyRequest().denyAll();
                });

        return http.build();
    }
}
