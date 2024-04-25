package com.luis.curso.springboot.app.springbootcrud.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luis.curso.springboot.app.springbootcrud.entities.User;
import com.luis.curso.springboot.app.springbootcrud.repositories.UserRepository;

@Service
/*Clase que por defecto se autoconfigura, pues al ser un Service que implementa
 * la interfaz UserDetailsService, se autoconfigura en la configuracion de 
 * SpringSecurityConfig. Por lo que es parte del contexto de seguridad de Spring
 * 
 * Por lo que Spring sabe, que cuando hagamos login por usuario, se utilizara esta
 * clase para obtener el username con seguridad
*/
public class JpaUserDetailsService implements UserDetailsService{
    /*UserDetailsService
     * Interfaz que se encarga de devolver data de un usuario
     * en forma de un objeto UserDetails
    */

    @Autowired
    private UserRepository userRepository;

    @Override
    /*Métod para buscar un user por username, se obtiene el username
     * de la vista de un formulario o request
    */
    @Transactional(readOnly = true)
    /*userDetails
     * Interface que provee de informacion de un usuario
    */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = this.userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(String
            .format("Username %s no existe en el sistema!!", username));
        }

        User user = userOptional.orElseThrow();
        //Convierte los roles a una lista de roles de tipo GratedAuthorities
        List<GrantedAuthority> authorities = user.getRoles().stream()
        /*GrantedAuthority
         * representa una Autoridad
         * Define que acciones un usuario puede llevar acabo
         * https://hyperskill.org/learn/step/35364
        */

        /*SimpleGrantedAuthority
         * Clase concreta que implementa GrantedAuthority, se le provee de una
         * casena ya que almacena esa cadena como forma de representación de una
         * Autoridad Garantizada para proveer acciones a un Usuario
        */
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
            /*Esta clase User es especial pues implementa la interfaz UserDetails
             * y funciona como un clase para devolver un usuario con seguridad.
             * 
             * El constructor de esta clase requiere parametros requeridos por la
             * clase DaoAuthenticationProvider
            */
            user.getUsername(), 
            user.getPassword(), 
            user.isEnabled(),
            true,
            true,
            true,
            authorities
            );
    }

}
