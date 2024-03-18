package com.luis.springboot.error.springbooterror.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.luis.springboot.error.springbooterror.controller.domain.User;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    @Qualifier("registersUsers")
    private List<User> users;

    @Override
    public List<User> findAll() {
        return this.users;
    }

    @Override
    public Optional<User> findID(int id) {
        /*Devuelve un Optional, esto es, si encuentra al usuario,
         * lo devuelve, sino, retorna un Optional con valor emty
         * e igual a Nulo
        */
        Optional<User> user = this.users.stream().filter(
            usr -> usr.getiD() == id).findFirst();
        /*for (User u : users) {
            if (u.getiD() == (id)) {
                user = u;
                break;
            }
        }*/
        return user;
        /*Funcion que devulve el usuaio si existe, de lo contrario, 
         * devuelve un empty. es un Optional nulo
         */
    }
    
}
