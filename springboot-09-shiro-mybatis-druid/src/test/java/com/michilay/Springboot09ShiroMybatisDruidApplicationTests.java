package com.michilay;

import com.michilay.pojo.User;
import com.michilay.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot09ShiroMybatisDruidApplicationTests {


    UserService userService;
    @Autowired
    public Springboot09ShiroMybatisDruidApplicationTests(UserService userService) {
        this.userService = userService;
    }

    @Test
    void contextLoads() {
        User michilay = userService.findUserByName("michilay");
        System.out.println(michilay);
    }

}
