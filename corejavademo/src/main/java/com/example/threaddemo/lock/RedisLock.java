package com.example.threaddemo.lock;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by hui.yunfei@qq.com on 2019/6/26
 */
//@Component
public class RedisLock {


    private static final Long RELEASE_SUCCESS = 1L;

    private static long timeout = 5000;

    public static boolean lock(String key, String value,RedisTemplate<String, Object> template) {
        long start = System.currentTimeMillis();
//        while (true) {
//            //检测是否超时
//            if (System.currentTimeMillis() - start > timeout) {
//                return false;
//            }
//            //执行set命令
//            Boolean absent = template.opsForValue().setIfAbsent(key, value, timeout, TimeUnit.MILLISECONDS);//1
//            //其实没必要判NULL，这里是为了程序的严谨而加的逻辑
//            if (absent == null) {
//                return false;
//            }
//            //是否成功获取锁
//            if (absent) {
//                return true;
//            }
//        }

        Boolean absent = template.opsForValue().setIfAbsent(key, value, timeout, TimeUnit.MILLISECONDS);//1
        return absent;
    }

    public static boolean unlock(String key, String value,RedisTemplate<String, Object> template,DefaultRedisScript<Long> redisScript) {
        //使用Lua脚本：先判断是否是自己设置的锁，再执行删除
        Long result = template.execute(redisScript, Arrays.asList(key,value));
        //返回最终结果
        return RELEASE_SUCCESS.equals(result);
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }



}
