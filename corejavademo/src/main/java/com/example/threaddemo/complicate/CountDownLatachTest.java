package com.example.threaddemo.complicate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * countDownLatach 在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待
 * Created by hui.yunfei@qq.com on 2019/6/21
 */
public class CountDownLatachTest {
    private static final ExecutorService service=Executors.newFixedThreadPool(2);
    private static final CountDownLatch count=new CountDownLatch(2);

    public static void main(String[] args) {
        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                count.countDown();
            }
        });

        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                count.countDown();
            }
        });
        try {
            count.await();//await会让main线程一直等待其他被countDown标志的线程执行完
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main Thread is ending");
    }
}
