package com.example.restdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by huiyunfei on 2019/4/12.
 */
@Data
@Builder//为类提供一个内部的Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestUserModel {

    private int id;

    private String username;

    private String password;


}
