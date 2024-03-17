package com.luis.springboot.error.springbooterror.services;

import java.util.List;

import com.luis.springboot.error.springbooterror.controller.domain.User;

public interface UserService {

    List<User> findAll();
    User findID(int id);
}
