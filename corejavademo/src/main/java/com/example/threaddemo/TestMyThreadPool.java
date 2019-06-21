package com.example.threaddemo;

import java.util.Random;

/**
 *类说明：测试自定义线程池实现
 */
public class TestMyThreadPool {
    private static  final  ThreadLocal<Integer> local=new ThreadLocal();
    private static  int number=0;
    public static void main(String[] args) throws InterruptedException {

        MyTask task = new MyTask("abc");
        Thread thread = new Thread(new MyTask("abc"));thread.start();
        Thread thread2 = new Thread(new MyTask("abc"));thread2.start();

    }

    // 任务类
    static class MyTask implements Runnable {

        private String name;
        private Random r = new Random();

        public MyTask(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {// 执行任务
          for(int i=0;i<10;i++){
        synchronized (MyTask.class) {
            number++;
        }

            try {
                Thread.sleep(200);
                System.out.println(Thread.currentThread().getName() + ":" + number);
            } catch (InterruptedException e) {

            }


          }
        }
    }

}
