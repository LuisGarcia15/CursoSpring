package com.luis.springboot.error.springbooterror.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.luis.springboot.error.springbooterror.controller.domain.User;

@Service
public class UserServiceImpl implements UserService{

    private List<User> users;

    public UserServiceImpl(){
        this.users = new ArrayList<>();
        users.add(new User(1, "Luis", "Garcia"));
        users.add(new User(2, "Pepe", "Navia"));
        users.add(new User(3, "Alan", "Lara"));
        users.add(new User(4, "Mario", "Santillan"));
        users.add(new User(5, "Alberto", "Gonzales"));
    }

    @Override
    public List<User> findAll() {
        return this.users;
    }

    @Override
    public User findID(int id) {
        User user = null;
        for (User u : users) {
            if (u.getiD() == (id)) {
                user = u;
                break;
            }
        }
        return user;
    }
    
}
