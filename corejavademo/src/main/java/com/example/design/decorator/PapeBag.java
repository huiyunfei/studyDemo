package com.example.design.decorator;

/**
 * Created by huiyunfei on 2019/9/3.
 */
public class PapeBag implements Bag {
    @Override
    public void pack() {
        System.out.println("原始的包装");
    }
}
