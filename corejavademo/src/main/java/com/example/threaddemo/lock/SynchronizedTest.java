package com.example.threaddemo.lock;

import com.example.threaddemo.utils.SleepTools;

/**
 * Created by hui.yunfei@qq.com on 2019/6/28
 */
public class SynchronizedTest {

    private static synchronized void synClass(){
        System.out.println(Thread.currentThread().getName()
                +"synClass going...");
        SleepTools.second(1);
        System.out.println(Thread.currentThread().getName()
                +"synClass end");
    }

    private static Object obj = new Object();
    private static void synStatic(){
        synchronized (obj){
            System.out.println(Thread.currentThread().getName()
                    +"synStatic going...");
            SleepTools.second(1);
            System.out.println(Thread.currentThread().getName()
                    +"synStatic end");
        }
    }

    public static void main(String[] args) {

    }
}

