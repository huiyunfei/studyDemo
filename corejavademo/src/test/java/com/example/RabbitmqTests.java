package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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

    @Test
    public void test(){

        Integer id=52;
        ExecutorService executorService= Executors.newFixedThreadPool(3);
        for(int i=0;i<3;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    synchronized (id){
                        System.out.println(Thread.currentThread().getName()+" start");
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+" end");
                    }
                }
            });

        }

    }





}













