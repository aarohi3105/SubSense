package com.aarohi.subsense.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey getSigningKey() {              //header
        return Keys.hmacShaKeyFor(                          //sha encoding algo is being used
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generateToken(String email) {         //access token main generating block

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())  //current date
                .expiration(
                        new Date(System.currentTimeMillis() + expiration)
                )
                .signWith(getSigningKey())
                .compact();
    }
}