package com.javanauta.agendadortarefas.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtUtil {

    private final String secretKey = "sua-chave-secreta-super-segura-que-deve-ser-bem-longa";

    // Extrai as claims do token
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Extrai o e-mail do token
    public String extrairEmailToken(String token) {
        return extractClaims(token).getSubject();
    }

    // Verifica se o token expirou
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // Valida o token
    public boolean validateToken(String token, String username) {
        String extractedUsername = extrairEmailToken(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }
}