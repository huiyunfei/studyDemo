package com.example.lock.ReentrantLock;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 *@author Mark老师   享学课堂 https://enjoy.ke.qq.com 
 *
 *更多课程咨询 安生老师 QQ：669100976  VIP课程咨询 依娜老师  QQ：2470523467
 *
 *类说明：测试Lock和Condition实现等待通知
 */
public class TestCond {
    private static ExpressCondOneLock express = new ExpressCondOneLock(0,ExpressCondOneLock.CITY);

    /*检查里程数变化的线程,不满足条件，线程一直等待*/
    private static class CheckKm extends Thread{
        @Override
        public void run() {
        	express.waitKm();
        }
    }

    /*检查地点变化的线程,不满足条件，线程一直等待*/
    private static class CheckSite extends Thread{
        @Override
        public void run() {
        	express.waitSite();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        try {
            Selector s= Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
