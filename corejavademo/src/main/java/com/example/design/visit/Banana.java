package com.example.design.visit;

/**
 * Created by huiyunfei on 2019/9/3.
 */
public class Banana implements Fruit {
    @Override
    public void print() {
        System.out.println("i m banana");
    }

    @Override
    public void accept(Visit visit) {
        //被调用者Banana告诉访问者visit，我，this给你了，你以后可以对我的对象进行操作了
        visit.sell(this);
    }
}
