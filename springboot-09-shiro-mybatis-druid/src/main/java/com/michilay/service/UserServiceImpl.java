package com.michilay.service;

import com.michilay.mapper.UserMapper;
import com.michilay.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UserService{

    UserMapper userMapper;
    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findUserByName(String userName) {
        return userMapper.findUserByName(userName);
    }
}
