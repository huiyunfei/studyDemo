package com.example.restdemo.entity;

import lombok.Data;

/**
 * Created by huiyunfei on 2019/4/12.
 */
@Data//lombok注解，可以省略set get等等代码
public class TestUser {

    private int id;

    private String username;

    private String password;


}
