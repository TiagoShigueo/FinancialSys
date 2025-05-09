package br.com.financialsys.backend.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.financialsys.backend.model.User;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create().withIssuer("user-api").withSubject(user.getName())
                    .withClaim("role", user.getRole())
                    .withExpiresAt(generateExpirationDate()).sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    // public String validateToken(String token) {
    public DecodedJWT validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // return
            // JWT.require(algorithm).withIssuer("user-api").build().verify(token).getSubject();
            return JWT.require(algorithm).withIssuer("user-api").build().verify(token);
        } catch (JWTVerificationException exception) {
            return null;
        }
    }
}
