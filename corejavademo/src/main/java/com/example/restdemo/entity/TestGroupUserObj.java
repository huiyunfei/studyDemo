package com.example.restdemo.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by hui.yunfei@qq.com on 2019/4/18
 */
@Data
public class TestGroupUserObj extends TestGroup{

    private List<TestUser> testUsers;
}
