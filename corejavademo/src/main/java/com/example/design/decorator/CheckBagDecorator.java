package com.example.design.decorator;

/**
 * Created by huiyunfei on 2019/9/3.
 */
public class CheckBagDecorator extends BagDecorator{

    public CheckBagDecorator(Bag bag) {
        super(bag);
    }

    public void pack(){
        super.pack();
        speed();
    }

    private void speed() {
        System.out.println("speed ...");
    }
}
