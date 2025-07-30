package com.aluralatam.foro.controller;

import com.aluralatam.foro.dto.AuthenticationRequest;
import com.aluralatam.foro.dto.AuthenticationResponse;
import com.aluralatam.foro.service.impl.AuthenticationServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest authRequest){
        AuthenticationResponse jwtDto = authenticationService.login(authRequest);
        return ResponseEntity.ok(jwtDto);
    }

    @GetMapping("/publico")
    public String publico(){
        return "Endpoint publico";
    }
}
