package com.example.design.decorator;

/**
 * Created by huiyunfei on 2019/9/3.
 */
public class SpeedBagDecorator extends BagDecorator{
    public SpeedBagDecorator(Bag bag) {
        super(bag);
    }

    public void pack(){
        super.pack();
        check();
    }

    private void check() {
        System.out.println("check ...");
    }
}
