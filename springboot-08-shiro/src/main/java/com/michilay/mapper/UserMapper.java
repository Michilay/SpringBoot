package com.michilay.mapper;


import com.michilay.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User findUserByName(String userName);
}
