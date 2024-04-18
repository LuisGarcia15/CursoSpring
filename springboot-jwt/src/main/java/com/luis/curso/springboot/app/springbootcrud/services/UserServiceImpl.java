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
    PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
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
        if(user.isAdmin()){
            Optional<Role> optionalRoleUser2 = this.roleRepository.findByName("ROLE_ADMIN");
            optionalRoleUser2.ifPresent(rol ->{
                roles.add(rol);
            });
        }
        user.setRoles(roles);
        String passwordEncoder = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoder);
        return this.userRepository.save(user);
    }
    
}
