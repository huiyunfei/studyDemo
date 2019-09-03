package com.example.design.visit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huiyunfei on 2019/9/3.
 */
public class Visit {

    public void sell(Apple apple){
        System.out.println("apple ￥10 ");
    }
    public void sell(Banana banana){
        System.out.println("banana ￥20 ");
    }
    public void sell(Fruit fruit){
        System.out.println("fruit ￥30 ");
    }


    public static void main(String[] args) {
        List<Fruit> list = new ArrayList<>();
        list.add(new Apple());
        list.add(new Banana());
        Visit visit=new Visit();
        for(Fruit fruit:list){
            fruit.accept(visit);
        }
    }
}





