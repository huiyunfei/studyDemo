package com.example.threaddemo.lock;

/**
 * Created by hui.yunfei@qq.com on 2019/6/20
 */
public class Volatile {

    private static  boolean ready=false;
    private static int number=50;

    static class  test implements Runnable{
        @Override
        public  void run(){
            System.out.println("PrintThread is running.......");
            while (!ready){

            };
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new Thread(new test()).start();
        try {
            Thread.sleep(300);
            number = 51;
            ready = true;
            Thread.sleep(300);
            System.out.println("Thread is stop");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}














