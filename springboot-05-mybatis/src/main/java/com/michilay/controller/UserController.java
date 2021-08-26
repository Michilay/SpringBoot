package com.michilay.controller;


import com.michilay.pojo.User;
import com.michilay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findAll")
    public List<User> findAll(){
        List<User> all = userService.findAll();
        return all;
    }
    @GetMapping("/addUser")
    public String addUser(){
        User user = new User();
        user.setUserName("boot");
        user.setUserPwd("213asd");
        user.setMoney(123D);
        userService.addUser(user);
        return "添加成功";
    }
    @GetMapping("/updateUser")
    public String updateUser(){
        User user = new User();
        user.setUserId(1);
        user.setMoney(1231232131232D);
        userService.updateUser(user);
        return "修改成功";
    }
    @GetMapping("/deleteUser")
    public String deleteUser(){
        userService.deleteUser(1);
        return "删除成功";
    }
}
