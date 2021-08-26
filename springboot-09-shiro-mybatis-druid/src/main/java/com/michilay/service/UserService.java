package com.michilay.service;

import com.michilay.pojo.User;

public interface UserService {

    User findUserByName(String userName);
}
