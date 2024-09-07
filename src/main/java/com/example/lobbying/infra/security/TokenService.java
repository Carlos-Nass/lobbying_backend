package com.example.lobbying.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.lobbying.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.token}")
    private String secretKey;

    public String generateToken(User user){
        try{

            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.create().withSubject(user.getEmail()).withExpiresAt(generateExpirationDate()).sign(algorithm);

        } catch (JWTCreationException e) {
            throw new RuntimeException(e);
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String Token){
        try{

            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.require(algorithm).build().verify(Token).getSubject();

        } catch (JWTDecodeException e) {
            return null;
        }
    }

}
