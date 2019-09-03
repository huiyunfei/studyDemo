package com.example.design.visit;

/**
 * Created by huiyunfei on 2019/9/3.
 */
public class Apple implements Fruit {
    @Override
    public void print() {
        System.out.println("i m apple");
    }

    @Override
    public void accept(Visit visit) {
        visit.sell(this);
    }
}
