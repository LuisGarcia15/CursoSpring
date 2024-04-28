package com.luis.curso.springboot.app.springbootcrud.security.filter;

import static com.luis.curso.springboot.app.springbootcrud.security.TokenJwtConfig.CONTENT_TYPE;
import static com.luis.curso.springboot.app.springbootcrud.security.TokenJwtConfig.HEADER_AUTHORIZATION;
import static com.luis.curso.springboot.app.springbootcrud.security.TokenJwtConfig.PREFIX_TOKEN;
import static com.luis.curso.springboot.app.springbootcrud.security.TokenJwtConfig.SECRET_KEY;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luis.curso.springboot.app.springbootcrud.security.SimpleGrantedAuthorityJsonCreator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Arrays;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidationFilter extends BasicAuthenticationFilter{
    /*Basic Authentication Filter
    Procesa los encabezados de autorización BÁSICA de una solicitud HTTP y 
    coloca el resultado en SecurityContextHolder. Procesa cualquier header
    que tenga el estandar de autenticación*/

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    /* doFilter()
    1-. Lee la solicitud entrante: El método doFilter() recibe la solicitud entrante y 
    la información de respuesta.
    2-. Verifica si la solicitud contiene credenciales de autenticación básica: Examina si 
    la solicitud HTTP incluye las credenciales de autenticación básica. Estas credenciales 
    generalmente se envían en el encabezado Authorization de la solicitud HTTP, en el 
    formato Basic [credenciales].
    3-. Extrae las credenciales de autenticación básica: Si la solicitud incluye credenciales de 
    autenticación básica, el filtro las extrae y las decodifica para obtener el nombre de 
    usuario y la contraseña.
    4-. Crea un objeto de autenticación: Utilizando las credenciales extraídas, el filtro crea un 
    objeto de autenticación que representa la solicitud de autenticación del usuario.
    Pasa la solicitud al AuthenticationManager: Una vez que se ha creado el objeto de autenticación, 
    el filtro lo pasa al AuthenticationManager para que lo procese y realice la autenticación del usuario.
    5-. Maneja la respuesta de autenticación: Dependiendo del resultado de la autenticación, el filtro 
    puede permitir que la solicitud continúe normalmente o puede devolver una respuesta de error de 
    autenticación si las credenciales son incorrectas.
    6-. Continúa el filtro de la cadena de filtros de seguridad: Después de manejar la autenticación básica, 
    el filtro pasa la solicitud al siguiente filtro en la cadena de filtros de seguridad de Spring Security.*/
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
            //el token se envia en las cabeceras
            //El nombre del token es Autorization
        String header = request.getHeader(HEADER_AUTHORIZATION);

        //bearer: prefijo por necesario obligatorio
        //lo incluye en token de login
        if(header == null || !header.startsWith(PREFIX_TOKEN)){
            //En caso de que el recurso no este protdigo, debe continuar con el filtro
            chain.doFilter(request, response);
            return;
        }
        //Eliminamos el prefijo y solo obtenemos el token
        String token = header.replace(PREFIX_TOKEN, "");
        
        try {
        //Obtiene los cliams
        Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
        //Obtiene el username
        String username = claims.getSubject();
        //String username = (String) claims.get("username");
        //Obtenemos los roles que estan bajo la llave "authorities" en una
        //representacion JSON
        Object authoritiesClaims = claims.get("authorities");
        Collection<? extends GrantedAuthority> authorities = Arrays.asList
        /*Se acopla el constructor de SimpleGrantedAuthority al cosntructor de 
         * SimpleGrantedAuthorityJsonCreator para que busque los roles por la llave
         * authorities y no por la llave role como lo tiene definido el constructor
         * SimpleGrantedAuthorities
        */
            (new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
            .readValue(authoritiesClaims.toString().getBytes(), SimpleGrantedAuthority[].class));
        //El password solo se valida cunado se crea el token, por eso las credenciales son null
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
        //Autenticacion
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    } catch (JwtException e) {
        Map<String, String> body = new HashMap<>();
        body.put("error", e.getMessage());
        body.put("message", "El token es invalido!");

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(CONTENT_TYPE);
        }
    }

    /*addMixIn()
     * Tenemos la clase SimpleGrantedAuthority que buscar los roles de un usuario por
     * "role" pero nuestra aplicación guardo los roles como "authorities", por lo que al
     * serializar los roles en un Objecto SimpleGrantedAuthority, no podra en contrar los roles
     * 
     * Usamos el método addMixIn(), es utilizado cuando queremos utilizar un codigo de una clase
     * que no puede modificarse pero necesitamos que hago algo de una forma especifica, en este
     * caso necesiamtos que SimpleGrantedAuthority busque los roles por Authorities y no por role
     * 
     * Por lo que creamos una clase nueva, SimpleGrantedAuthorityJsonCreator que debe ser abstracta 
     * donde su constructor buscara los roles por el valor de la llave en los claims "authorities"
     * 
     * Por lo que addMixIn() perimte acoplar un codigo personalizado a un codigo que no puede
     * ser modificado para que realizce cierta acción
    */
    
}
