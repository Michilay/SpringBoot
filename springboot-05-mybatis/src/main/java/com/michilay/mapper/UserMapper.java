package com.michilay.mapper;


import com.michilay.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    List<User> findAll();
    User findUserById(int id);
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(int id);
}
