package com.michilay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JdbcController {

    @RequestMapping("/t1")
    public String test(){
        return "hello springboot";
    }
    @Autowired
    JdbcTemplate jdbcTemplate;
//  查询
    @GetMapping("/userList")
    public List<Map<String,Object>> userList(){
        String sql = "select * from count_db.account";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }
//  添加
    @GetMapping("/addUser")
    public String addUser(){
        String sql = "insert into count_db.account(userId,userName,userPwd,money) values (2,'123123','123123',123.1)";
        jdbcTemplate.update(sql);
//        自动提交事务
        return "add OK!";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id){
        String sql = "update count_db.account set userName =?,userPwd=?,money=?  where userId=" + id;
        Object[] objects = new Object[3];
        objects[0] = "mcly111";
        objects[1] = "mcly1111";
        objects[2] = "123.123";
        jdbcTemplate.update(sql,objects);
        return "update OK!";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id){
        String sql = "delete from count_db.account where userId = ?";
        jdbcTemplate.update(sql,id);
        return "delete OK!";
    }
}
