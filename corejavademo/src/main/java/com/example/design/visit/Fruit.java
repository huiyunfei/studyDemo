package com.example.design.visit;

/**
 * Created by huiyunfei on 2019/9/3.
 */
public interface Fruit {
    public void print();

    //把访问者对象作为形参放入业务接口，实现类中重写业务方法调用访问者操作方法
    public void accept(Visit visit);
}
