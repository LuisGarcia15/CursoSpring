package com.luis.curso.springboot.app.springbootcrud.security.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luis.curso.springboot.app.springbootcrud.entities.User;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static com.luis.curso.springboot.app.springbootcrud.security.TokenJwtConfig.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    /*UsernamePasswordAuthenticationFilter
     * Procesa una autenticacion enviada. necesita dos parametros, un 
     * username y un password
    */

    private AuthenticationManager authenticationManager;
    

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager){
        /*AutenticationManager
         * Interfaz que procesa una petición de Autenticación
        */
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        /* -retornar una autenticación si es exitosa para un usuario
         * -retorna null si la autenticación sigue en progreso y necesita más
         * tratamiento
         * retorna una autenticación si la exepción falla
         * 
         * Retorna un objeto de autenticación
         * Este objeto representa un token para una autenticación o para
         * una autenticación principar una vez el request haya sido procesado
         * por el métro authenticate() de la interfaz AutenticationManager
        */
        User user = null;
        String username = null;
        String password = null;

        try {
            user = new ObjectMapper().readValue(request.getInputStream(),
            User.class);
            /*ObjectMapper es una clase envotoria que se encarga de tranformar
             * un objeto contenido en formato JSON en formato Java
            */
            username = user.getUsername();
            /*Obtenemos los campos de username y password para valirdar el
             * login
            */
            password = user.getPassword();
        } catch (StreamReadException e) {
            
        } catch (DatabindException e) {
            
        } catch (IOException e) {
            
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        /*Implenetación de la interfaz Authentication
         * Clase diseñanada para representar o presentar un username y password
         * Es la representación de los tokens para la autenticación
        */
        return this.authenticationManager.authenticate(authenticationToken);
        /*Por detras de escena, authenticate() llama a JPAUserDetailsService para
         * autenticar con respecto a la DB
        */
}

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                Authentication authResult) throws IOException, ServletException {

                       /*Define el comportamiento de una Authenticación existosa
                        * 1-. Añade el Objeto Authentication que fue existosos en el
                        * contexto de seguridad
                        */
                
                User user = (User)authResult.getPrincipal();
                /*Se utiliza User de Spring Secutiry por que es una implementacion
                 * de Authentication
                */
                String username = user.getUsername();
                Collection <? extends GrantedAuthority> roles = authResult.getAuthorities();
                
                String token = Jwts.builder()
                .subject(username)
                .claim("authorities", roles)
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .issuedAt(new Date())
                .signWith(SECRET_KEY)
                .compact();
                /*Crea el token del usuario que hizo login*/
                response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);
                Map<String, String> body = new HashMap<>();
                body.put("token", token);
                body.put("username", username);
                body.put("message", String.format(
                    "Hola, has[ %s ] iniciado sesión con exito", username));
                response.getWriter().write(new ObjectMapper() .writeValueAsString(body));
                /*Serializa un objeto java en un String*/
                response.setContentType("application/json");
                response.setStatus(200);
        }
    
}
