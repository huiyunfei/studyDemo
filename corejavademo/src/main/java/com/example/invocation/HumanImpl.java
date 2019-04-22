package com.example.invocation;

/**
 * Created by hui.yunfei@qq.com on 2019/4/22
 */
public class HumanImpl implements Human {
    @Override
    public void eat(String food) {
        System.out.println("eat food:"+food);
    }
}
