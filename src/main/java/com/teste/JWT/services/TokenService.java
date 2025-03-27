package com.teste.JWT.services;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.teste.JWT.models.UserModel;

//Classe de serviço de token jwt
@Service
public class TokenService {
    
    private String secreteKey = "chavesecreta"; // Deve vir de algum lugar seguro como variaveis de ambiente

    // Metodo responsável por criar token
    public String generateToken(UserModel model){

        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secreteKey);

            String token = JWT.create()
                .withIssuer("JWT_SERVICE") // nome qualquer para identificar o service
                .withSubject(model.getEmail())
                .withExpiresAt(LocalDateTime.now().plusHours(2).atZone(ZoneId.systemDefault()).toInstant())
                .sign(algorithm);
        
            return token;

        } catch (JWTCreationException ex) {
            return null;
        }

        
    }

    // Metodo responsavel por validar o token
    public String validateToken(String token){
        try{

            Algorithm algorithm = Algorithm.HMAC256(this.secreteKey);

            return JWT.require(algorithm)
                .withIssuer("JWT_SERVICE")
                .build()
                .verify(token)
                .getSubject();


        }catch(JWTDecodeException ex){
            return null;
        }
    }

}
