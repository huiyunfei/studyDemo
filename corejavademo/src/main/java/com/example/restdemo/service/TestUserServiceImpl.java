package com.example.restdemo.service;

import com.example.restdemo.entity.TestGroup;
import com.example.restdemo.entity.TestUser;
import com.example.restdemo.mapper.TestUserDao;
import com.example.restdemo.model.TestUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by huiyunfei on 2019/4/12.
 */
@Service
public class TestUserServiceImpl implements TestUserService{

    @Autowired
    private TestUserDao testUserDao;

    @Override
    public TestUser findById(int id) {
        return testUserDao.findById(id);
    }

    @Override
    public void update(TestUserModel userModel) {
        this.testUserDao.update(userModel);
    }

    /**
     * @Description:事务必须保证MySQL引擎为innnoDB，方法是public，紧紧发生在RuntimeException或者error
     * 一般异常需要回滚添加@Transactional(rollbackFor=Exception.class)
     * 主启动应用添加@EnableTransactionManagement注解
     * @Author:huiyunfei
     * @Date: 2019/4/15
     */
    @Transactional
    @Override
    public void testTrancation (TestUserModel userModel) throws Exception{
        this.testUserDao.update(userModel);
        throw new RuntimeException("发生异常了..");
    }

    @Override
    public List<TestGroup> findGroupById(int groupNo) {
        return testUserDao.findGroupById(groupNo);
    }
}
