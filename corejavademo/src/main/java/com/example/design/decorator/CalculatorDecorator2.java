package com.example.design.decorator;

/**
 * Created by hui.yunfei@qq.com on 2019/9/22
 */
public class CalculatorDecorator2 implements Calculator {

    private Calculator calculator;

    public CalculatorDecorator2(Calculator calculator){
        this.calculator=calculator;
    }
    @Override
    public void caculator(int x, int y) {
        System.out.println("123456");

        calculator.caculator(x,y);
    }
}
