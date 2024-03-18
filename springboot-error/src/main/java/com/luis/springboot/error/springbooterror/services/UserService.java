package com.luis.springboot.error.springbooterror.services;

import java.util.List;
import java.util.Optional;

import com.luis.springboot.error.springbooterror.controller.domain.User;

public interface UserService {

    List<User> findAll();
    Optional<User> findID(int id);
}
