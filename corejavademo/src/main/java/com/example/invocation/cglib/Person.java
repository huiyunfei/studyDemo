package com.example.invocation.cglib;

/**
 * cgilib
 * Created by hui.yunfei@qq.com on 2019/4/22
 */
public class Person {
    public void before(){
        System.out.println("i'm a person ,i'm eatting before");
    }
    public void eat(String name){
        System.out.println("i'm a person ,i'm eatting "+name);
    }
    public void after(){
        System.out.println("i'm a person ,i'm eatting after");
    }

    /**
     * 该方法不能被子类覆盖,Cglib是无法代理final修饰的方法的
     */
    final public String sayOthers(String name) {
        System.out.println("i'm a person ,i'm eatting other "+name);
        return null;
    }
}













