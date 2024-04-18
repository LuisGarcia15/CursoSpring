package com.luis.curso.springboot.app.springbootcrud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        //Interfaz de servicio para encriptar contraseñas
        return new BCryptPasswordEncoder();
        //BCryptPasswordEncoder
        /*Implementación de PasswordEncoder que utiliza la 
        función de hash fuerte de BCrypt.*/
    }
}
