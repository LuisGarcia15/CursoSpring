package com.luis.curso.springboot.app.springbootcrud.security.filter;

import static com.luis.curso.springboot.app.springbootcrud.security.TokenJwtConfig.HEADER_AUTHORIZATION;
import static com.luis.curso.springboot.app.springbootcrud.security.TokenJwtConfig.PREFIX_TOKEN;
import static com.luis.curso.springboot.app.springbootcrud.security.TokenJwtConfig.SECRET_KEY;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidationFilter extends BasicAuthenticationFilter{

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        //TODO Auto-generated constructor stub
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
            //el token se envia en las cabeceras
            //El nombre del token es Autorization
        String header = request.getHeader(HEADER_AUTHORIZATION);

        //bearer: prefijo por necesario obligatorio
        //lo incluye en token de login
        if(header == null || !header.startsWith(PREFIX_TOKEN)){
            return;
        }
        //Eliminamos el prefijo y solo obtenemos el token
        String token = header.replace(PREFIX_TOKEN, "");
        
        try {
        //Obtiene los cliams
        Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
        } catch (JwtException e) {
        // TODO: handle exception
        }
    }

    
    
}
