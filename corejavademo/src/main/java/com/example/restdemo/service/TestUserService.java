package com.example.restdemo.service;

import com.example.restdemo.entity.TestGroup;
import com.example.restdemo.entity.TestUser;
import com.example.restdemo.model.TestUserModel;

import java.util.List;

/**
 * Created by huiyunfei on 2019/4/12.
 */
public interface TestUserService {

    public TestUser findById(int id);

    void update(TestUserModel userModel);

    void testTrancation (TestUserModel userModel) throws Exception;

    List<TestGroup> findGroupById(int groupNo);
}
