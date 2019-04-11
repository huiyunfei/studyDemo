package com.example.annotiondemo.demo;

/**
 * 模拟自己写的方法
 * Created by huiyunfei on 2019/4/11.
 */
public class MyMethod {

    public void methodNoCheck(){
        System.out.println("这是我写的一个不需要检查的方法");
    }

    @CheckInterface
    public void methodCheck(String value){
        System.out.println("这是我写的一个需要检查的方法,参数为："+value);
    }
}
