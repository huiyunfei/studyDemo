package com.example.threaddemo.complicate;

import java.util.List;

/**
 * Created by hui.yunfei@qq.com on 2019/6/21
 */
public class ExchangeTest {

    static class Producter implements Runnable{

        private List<String> buffer;
        @Override
        public void run() {
            for(int i=0;i<5;i++){
                System.out.println("生产者第"+i+"次生产数据");
                for(int j=0;j<5;j++){
                    System.out.println("生产者生产数据："+j);
                }
            }
        }
    }

    static class Consumer implements Runnable{

        private List<String> buffer;
        @Override
        public void run() {

        }
    }
}




















