package com.example.restdemo.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by hui.yunfei@qq.com on 2019/5/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(value={"DEPLOY=dev"})
public class RedisConfigurationTest {
    @Autowired
    @Qualifier("redisTemplate1")
    private RedisTemplate<String, Object> redisTemplate1;


    @Test
    public void redisStr() {
        redisTemplate1.opsForValue().set("user:test:A","a");
        String avalue= (String) redisTemplate1.opsForValue().get("user:test:A");
        redisTemplate1.boundValueOps("user:test:B").set("b");
        String bvalue=(String)redisTemplate1.boundValueOps("user:test:B").get();
        System.out.println("a:"+avalue+"||b:"+bvalue);
    }
    @Test
    public void redisHash() {
        redisTemplate1.opsForHash().put("user:test:A","a","avalue");
        String avalue= (String) redisTemplate1.opsForHash().get("user:test:A","a");
        redisTemplate1.boundHashOps("user:test:B").put("b","bValue");
        String bvalue=(String)redisTemplate1.boundHashOps("user:test:B").get("b");
        System.out.println("a:"+avalue+"||b:"+bvalue);
    }

}