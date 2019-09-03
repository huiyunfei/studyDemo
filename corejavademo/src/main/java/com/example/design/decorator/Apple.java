package com.example.design.decorator;

/**
 * Created by huiyunfei on 2019/9/3.
 */
public class Apple {
    public Apple() {
        System.out.println("生产出了一个苹果");
    }


    public static void main(String[] args) {
        Apple apple=new Apple();
        Bag bag = new PapeBag();
        bag = new CheckBagDecorator(bag);
        bag = new SpeedBagDecorator(bag);
        bag.pack();
    }
}
