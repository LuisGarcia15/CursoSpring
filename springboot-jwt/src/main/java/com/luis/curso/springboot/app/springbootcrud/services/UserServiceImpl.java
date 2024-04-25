package com.luis.curso.springboot.app.springbootcrud.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luis.curso.springboot.app.springbootcrud.entities.Role;
import com.luis.curso.springboot.app.springbootcrud.entities.User;
import com.luis.curso.springboot.app.springbootcrud.repositories.RoleRepository;
import com.luis.curso.springboot.app.springbootcrud.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    //Interfaz de servicio para encriptar passwords
    //Inyecta un objeto de tipo BCryptPasswordEncoder que 
    //implementa una interfaz de PasswordEncoder
    PasswordEncoder passwordEncoder;

    @Autowired
    //Interfaz que exteiende de CrudReposiroty para consultar los
    //datos de la tabla roles
    private RoleRepository roleRepository;

    @Autowired
    //Interfaz que exteiende de CrudReposiroty para consultar los
    //datos de la tabla users
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) this.userRepository.findAll();
    }

    @Override
    public User save(User user) {
        //Todo usuario tendra el role User
        Optional<Role> optionalRoleUser = this.roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        optionalRoleUser.ifPresent(rol -> {
            roles.add(rol);
        });
        //Verifica si el usuario es Admin con la bandera
        //Esto gracias a que el JSON tendra ese campo en false o true
        if(user.isAdmin()){
            //Obtiene el rol de Admin
            Optional<Role> optionalRoleUser2 = this.roleRepository.findByName("ROLE_ADMIN");
            optionalRoleUser2.ifPresent(rol ->{
                //Si existe el rol, lo agregamos a la lista de roles
                //que se le agregarpa al nuevo Usuario
                roles.add(rol);
            });
        }
        user.setRoles(roles);
        String passwordEncoder = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoder);
        return this.userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        /*Con CrudRepository traemos el valor booleano si existe
         * el Username, ya que hace una consulta basado en el nombre
        */
        return this.userRepository.existsByUsername(username);
       }
    
}
