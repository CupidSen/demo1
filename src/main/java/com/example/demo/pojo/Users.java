package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//用户
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private Integer id;
    private String email;
    private String password;
    private String name;
    private String verification;
}
