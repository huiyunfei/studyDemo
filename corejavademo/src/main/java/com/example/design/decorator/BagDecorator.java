package com.example.design.decorator;

/**
 * Created by huiyunfei on 2019/9/3.
 */
public class BagDecorator implements Bag {


    private Bag bag;
    public BagDecorator(Bag bag){
        this.bag=bag;
    }
    @Override
    public void pack() {
        bag.pack();
    }
}
