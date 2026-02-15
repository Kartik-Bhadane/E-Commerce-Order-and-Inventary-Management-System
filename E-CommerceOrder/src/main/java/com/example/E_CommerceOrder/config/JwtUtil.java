package com.example.E_CommerceOrder.config;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    // 🔐 SECRET KEY (KEEP SAFE)
    private static final String SECRET_KEY =
            "mysecretkeymysecretkeymysecretkey12345"; // min 32 chars

    private static final long EXPIRATION_TIME =
            1000 * 60 * 60 * 10; // 10 hours

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // ================================
    // GENERATE TOKEN
    // ================================
    public String generateToken(String username, String role) {

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ================================
    // EXTRACT CLAIMS
    // ================================
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ================================
    // EXTRACT USERNAME
    // ================================
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // ================================
    // EXTRACT ROLE
    // ================================
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // ================================
    // CHECK EXPIRATION
    // ================================
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    // ================================
    // VALIDATE TOKEN
    // ================================
    public boolean validateToken(String token) {
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
}