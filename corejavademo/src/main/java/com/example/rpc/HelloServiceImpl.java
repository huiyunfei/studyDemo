package com.example.rpc;

/**
 * Created by hui.yunfei@qq.com on 2019/9/5
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "hello "+name;
    }
}
