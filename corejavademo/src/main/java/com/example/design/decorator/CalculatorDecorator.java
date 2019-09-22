package com.example.design.decorator;

/**
 * Created by hui.yunfei@qq.com on 2019/9/22
 */
public class CalculatorDecorator implements Calculator{

    private Calculator calculator;

    public CalculatorDecorator(Calculator calculator){
        this.calculator=calculator;
    }


    @Override
    public void caculator(int x, int y) {
        System.out.println(456789);
        calculator.caculator(x,y);
    }

    public static void main(String[] args) {
        Calculator decorator = new CalculatorDecorator(new CalculatorDecorator2((new CalculatorImpl())));
        decorator.caculator(1,2);
    }
}

