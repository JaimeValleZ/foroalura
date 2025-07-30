package com.aluralatam.foro.dto;

public record AuthenticationRequest(
        String username,
        String password
) {
}
