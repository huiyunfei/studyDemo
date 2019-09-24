package com.example.design.adapter;

/**
 * Created by hui.yunfei@qq.com on 2019/9/22
 */
public class Man implements Peo{
    private Girl p;

    public Man(Girl p){
        this.p=p;
    }
    @Override
    public void sleep() {
        p.sleep();
    }

    public static void main(String[] args) {
        Peo man = new Man(new Girl());
        man.sleep();
    }
}
