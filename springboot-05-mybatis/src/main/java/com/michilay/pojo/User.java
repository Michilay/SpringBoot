package com.michilay.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userId;
    private String userName;
    private String userPwd;
    private Double money;
}
