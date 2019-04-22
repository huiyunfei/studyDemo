package com.example.invocation;

/**
 * 静态代理类
 * 也需要实现接口
 * Created by hui.yunfei@qq.com on 2019/4/22
 * 添加代理类HumanProxy实现Human
 * 提供Human的私有对象
 * 提供实例化HumanImpl对象的返回HumanProxy类型构造器
 * 在HumanProxy重载方法中调用Human对象操作另一个实例类来操作
 */
public class HumanProxy implements Human{


    private Human human;

    public HumanProxy(){
        human=new HumanImpl();
    }

    @Override
    public void eat(String food) {
        before();
        human.eat(food);
        after();
    }

    public void before(){
        System.out.println("before...");
    }
    public void after(){
        System.out.println("after...");
    }

    public static void main(String[] args) {
        Human human=new HumanProxy();
        human.eat("noods");
    }
}
