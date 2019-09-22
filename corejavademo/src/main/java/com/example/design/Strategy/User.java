package com.example.design.Strategy;

/**
 * Created by hui.yunfei@qq.com on 2019/9/22
 */
public class User {

    public Calculator calculator;

    public User(Calculator calculator){
        this.calculator=calculator;
    }

    public void calculator(int x,int y){
        calculator.caculator(x,y);
    }

    public static void main(String[] args) {
        User user = new User(new CalculatorAdd());
        User user2 = new User(new CalculatorRemove());
        user.calculator(2,1);
        user2.calculator(2,1);
    }
}
