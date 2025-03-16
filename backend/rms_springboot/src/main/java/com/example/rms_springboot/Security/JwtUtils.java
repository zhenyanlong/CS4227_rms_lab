package com.example.rms_springboot.Security;

import com.example.rms_springboot.DataModel.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}") // Make sure that application.properties is configured
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;




    // Token generation method
    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration * 1000))
                .sign(Algorithm.HMAC256(secret));
    }


    // validate Token
    public DecodedJWT validateToken(String token) {
        return JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token);
    }


}