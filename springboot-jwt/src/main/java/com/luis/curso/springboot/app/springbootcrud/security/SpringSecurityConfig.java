package com.luis.curso.springboot.app.springbootcrud.security;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.luis.curso.springboot.app.springbootcrud.security.filter.JwtAuthenticationFilter;
import com.luis.curso.springboot.app.springbootcrud.security.filter.JwtValidationFilter;

import java.util.Arrays;

@Configuration
//@EnableMethodSecurity(prePostEnabled = true)
/*Habilita las anotaciones para seguridad como @PreAuthorized*/
public class SpringSecurityConfig {

    @Autowired
    /*Clase que maneja la configuración de autenticación*/
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception{
        /*Permite generar y obtener el Autentication Manager de la
         * aplicacion de SpringSecurity
        */
        return this.authenticationConfiguration.getAuthenticationManager();
        /*se utiliza en Spring Security para obtener el AuthenticationManager. Este AuthenticationManager 
        es responsable de autenticar las solicitudes de seguridad, como el inicio de sesión de un usuario.*/
    }

    @Bean
    //Componente que se inyectara para encriptar contraseñas
    public PasswordEncoder passwordEncoder(){
        //Interfaz de servicio para encriptar contraseñas
        return new BCryptPasswordEncoder();
        //BCryptPasswordEncoder
        /*Implementación de PasswordEncoder que utiliza la 
        función de hash fuerte de BCrypt.*/
    }

    @Bean
    /*Incluye las reglas de SEGURIDAD*/


    /*Devuelve un filtro que valida los request y va a dar permisos
     * o denegar permisos
     * 
     * Inyecta una instancia de SecurityFilterChain
     * 
     * Filter Chain es un objeto de filtro de seguridad que se aplica
     * a solicitudes HTTP. Se usa para configurar un FilterChainProxy
    */
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        /*HttpSecurity
         * Permite definir configuracion se seguridad web para ciertos request
         * Por defecto se aplica para todo request pero se puede restringir con
         * el método requestMatchers() de la clase RequestMatcher
        */
        return http.authorizeHttpRequests(authz ->
        //Ruta para dar seguridad o permitir permisos
        /*Las rutas de /users quedan libres de autenticacion, todo
         * los demás enpoints requieren autorización
        */
        authz.requestMatchers(HttpMethod.GET, "/api/users").permitAll()
        /*Se puede tener tantos requestMatchers como se requiera. Para indicar el
         *metodo o el verbo de se coloca antes de la ruta el verbo que es aplicado
         *por la ruta
        */
        //Para indicar el rol, no se indica el prefijo ROLE_
        .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
        .requestMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET, "/api/products", "/api/products/{id}").hasAnyRole("ADMIN", "USER")
        .requestMatchers(HttpMethod.POST, "/api/products").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PUT, "/api/products/{id}").hasRole("ADMIN")
        .requestMatchers(HttpMethod.DELETE, "/api/products/{id}").hasRole("ADMIN")
        .anyRequest().authenticated())
        //Se agrega el filtro de autenticación que se creo en la clase JwtAuthenticationFilter
        .addFilter(new JwtAuthenticationFilter(this.authenticationManager()))
        //Se agrega el filtro de validation que se creo en la clase JwtValidationFilter
        .addFilter(new JwtValidationFilter(this.authenticationManager()))
        /*csrf
         * Token (valor secreto unico )de seguridad que se genera
         * por parte del servidor. Generar el token genera seguridad
         * contra vulneravilidades. Se usa en usos de formularios o
         * JCP
         * 
         * Este viene por defecto activado, y aqui se desactiva
         * pues no estamos trabajando con vistas o JCP, estamos
         * trabajando con Api REST, solo se usa con vistas.
         * Se desactiva la proteccion CSRF
        */
        .csrf(config -> config.disable())
        /*sessionManagment
         * La creacion de la sesion de usario (sessionCreationPolicy) por
         * defecto es conectado, osea que se guarda en la sesion HTTP, del
         * servidor.
         * 
         * Aqui alteramos eso pues la sesion se va a enviar en tokens y
         * cada vez que se haga un request se envia un token
         * 
         * Lo colocamos en sin estado para poder validar tokens con info de usuarios
        */
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .sessionManagement(managment -> 
        /*sessionManagement permite generar configuracions de una sesion httm*/
            managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
    }

    /*Configuracion de cors
     * para cuando un cliente externo a nuestro servidor (como angular)
    */
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        /*Elementos de cabecera que puedes encontrar en la documentacion*/
        config.setAllowCredentials(true);

        //Clase que implementa CorsConfigurationSource
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //La configuracion se va a aplicar a toda la app con /**
        source.registerCorsConfiguration("/**", config);
        return source;
    }
    
    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter(){
        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<>(
            new CorsFilter());
            corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
            return corsBean;
    }
}
 