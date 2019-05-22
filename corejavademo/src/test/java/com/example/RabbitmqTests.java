package com.example;

import com.example.rabbitmq.direct.HelloSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
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
public class RabbitmqTests {

    @Autowired
    private HelloSender sender;

    @Autowired
    @Qualifier("redisTemplate1")
    private RedisTemplate<String, Object> redisTemplate1;


    @Test
    public void redis() {
        redisTemplate1.opsForValue().set("user:test:A","a");
        String avalue= (String) redisTemplate1.opsForValue().get("user:test:A");
        redisTemplate1.boundValueOps("user:test:B").set("b");
        String bvalue=(String)redisTemplate1.boundValueOps("user:test:B").get();
        System.out.println("a:"+avalue+"||b:"+bvalue);
    }




}













