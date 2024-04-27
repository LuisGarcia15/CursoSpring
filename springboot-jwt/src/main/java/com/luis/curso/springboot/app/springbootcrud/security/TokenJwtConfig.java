package com.luis.curso.springboot.app.springbootcrud.security;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.Jwts;

@Configuration
public class TokenJwtConfig {

    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    /*Crea la llave de seguridad para firmar los tokens*/
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
    /*Son publicas para que puedan ser utilizadas desde otras clases*/
}
