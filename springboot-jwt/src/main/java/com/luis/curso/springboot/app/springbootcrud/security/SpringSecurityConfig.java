package com.luis.curso.springboot.app.springbootcrud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests(authz ->
        authz.requestMatchers("/users").permitAll().anyRequest().authenticated())
        .csrf(config -> config.disable()).sessionManagement(managment -> 
            managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
    }
}
