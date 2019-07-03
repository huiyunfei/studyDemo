package com.example.jvm;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hui.yunfei@qq.com on 2019/6/24
 */
public class TestJvm {

    static class Demo{
        public Demo() {
        }
    }
    public static void main2(String[] args) {
        List list=new LinkedList();
        while (true){
            list.add(new Object());
        }
    }

    public static void main(String[] args) {
        byte[] bytes = new byte[4*1024*1024];

    }
}
