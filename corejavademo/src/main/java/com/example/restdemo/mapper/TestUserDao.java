package com.example.restdemo.mapper;

import com.example.restdemo.entity.TestUser;
import com.example.restdemo.model.TestUserModel;

/**
 * Created by huiyunfei on 2019/4/12.
 * 添加了@Mapper注解之后这个接口在编译时会生成相应的实现类
 *
 * 需要注意的是：这个接口中不可以定义同名的方法，因为会生成相同的id
 * 也就是说这个接口是不支持重载的
 *
 *@repository是用来注解接口，交给spring管理
 * 类似mybatis配置文件添加
 *<bean class="org.mybatis.spring.pammer.MapperScannerConfigure"
 *  p:basePachage="com.xxx.*.dao" p:sqlSessionFactoryBeanName="sessionFactory">
 * </bean>
 *
 */
//@Mapper
//@Repository
public interface TestUserDao {
    TestUser findById(int id);

    void update(TestUserModel userModel);

}
