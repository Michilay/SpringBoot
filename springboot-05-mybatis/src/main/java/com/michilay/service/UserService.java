package com.michilay.service;

import com.michilay.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findUserById(int id);
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(int id);
}
