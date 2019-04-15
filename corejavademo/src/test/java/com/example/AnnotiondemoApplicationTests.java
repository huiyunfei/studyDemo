package com.example;

import com.example.restdemo.model.TestUserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Description:单元测试路径要与主程序目录一致
 * 单元测试可以直接设置参数
 * @Author:hui.yunfei@qq.com
 * @Date: 2019/4/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(value={"DEPLOY=dev"})
//@ContextConfiguration(locations = {"classpath:application*.yml"})
public class AnnotiondemoApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(123);
    }

    @Test
    public void Builder() {

        TestUserModel user=new TestUserModel().builder().id(1).username("yunfei").password("123").build();
        System.out.println(user.getId()+user.getUsername());
    }


}
