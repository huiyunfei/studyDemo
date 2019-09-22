package com.example.design.Strategy;

/**
 * Created by hui.yunfei@qq.com on 2019/9/22
 */
public class CalculatorAdd implements Calculator{
    @Override
    public void caculator(int x, int y) {
        System.out.println("x+y:"+(x+y));
    }
}
