package com.example.restdemo.config;

import com.example.threaddemo.lock.RedisLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hui.yunfei@qq.com on 2019/5/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(value={"DEPLOY=dev"})
@Component
public class RedisConfigurationTest {
    @Autowired
    @Qualifier("redisTemplate1")
    private RedisTemplate<String, Object> redisTemplate1;
    @Autowired
    private DefaultRedisScript<Long> redisScript;



    //@Test
    public void redisStr() {
        redisTemplate1.opsForValue().set("user:test:A","a");
        String avalue= (String) redisTemplate1.opsForValue().get("user:test:A");
        redisTemplate1.boundValueOps("user:test:B").set("b");
        String bvalue=(String)redisTemplate1.boundValueOps("user:test:B").get();
        System.out.println("a:"+avalue+"||b:"+bvalue);
    }
    //@Test
    public void redisHash() {
        redisTemplate1.opsForHash().put("user:test:A","a","avalue");
        String avalue= (String) redisTemplate1.opsForHash().get("user:test:A","a");
        redisTemplate1.boundHashOps("user:test:B").put("b","bValue");
        String bvalue=(String)redisTemplate1.boundHashOps("user:test:B").get("b");
        System.out.println("a:"+avalue+"||b:"+bvalue);
    }

    @Test
    public void testLock(){
        ExecutorService executorService= Executors.newFixedThreadPool(3);
        for (int i=0;i<3;i++){
            String uuid=UUID.randomUUID().toString();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("thread in ..."+Thread.currentThread().getName());
                    boolean flag= RedisLock.lock("lock:timer2", uuid,redisTemplate1);
                    //Boolean flag = redisTemplate1.opsForValue().setIfAbsent("lock:timer", uuid, 3000, TimeUnit.MILLISECONDS);//1
                    System.out.println(Thread.currentThread().getName()+"开启锁："+flag);
                    if(flag){
                        System.out.println(Thread.currentThread().getName()+"正常执行");

                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        boolean result=RedisLock.unlock("lock:timer2",uuid,redisTemplate1,redisScript);
                        //Long result = redisTemplate1.execute(redisScript, Arrays.asList("lock:timer",uuid));
                        System.out.println(Thread.currentThread().getName()+"释放锁："+result);
                    }else{
                        System.out.println(Thread.currentThread().getName()+"退出");
                    }
                }
            });

        }

        redisTemplate1.opsForValue().set("test","yunfei222");
        System.out.println("get value:"+redisTemplate1.opsForValue().get("test"));

    }


}