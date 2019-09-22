package com.example.design.decorator;

/**
 * Created by hui.yunfei@qq.com on 2019/9/22
 */
public class CalculatorImpl implements Calculator{
    @Override
    public void caculator(int x, int y) {
        System.out.println("业务实现类 x+y="+(x+y));
    }
}
