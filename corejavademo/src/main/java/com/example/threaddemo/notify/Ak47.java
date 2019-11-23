package com.example.threaddemo.notify;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hui.yunfei@qq.com on 2019/9/20
 */
public class Ak47{

    private static int count=0;
    static ReentrantLock lock = new ReentrantLock();
    static Condition addCondition=lock.newCondition();
    static Condition removeCondition=lock.newCondition();

    public static void add() throws InterruptedException {
        lock.lock();
        if(count<10){
            Thread.sleep(200);
            count++;
            removeCondition.signal();
            System.out.println("count ++:"+count);
        }else{
            Thread.sleep(200);
            System.out.println("count ++ wait");
            addCondition.await();
        }
        lock.unlock();
    }


    public static void remove() throws InterruptedException {
        lock.lock();
        if(count>0){
            Thread.sleep(200);
            count--;
            System.out.println("count --:"+count);
            addCondition.signal();
        }else{
            Thread.sleep(200);
            System.out.println("count -- wait");
            removeCondition.await();
        }
        lock.unlock();
    }

    public static void main(String[] args) throws Exception{
        for(int i=0;i<5;i++){
            new Thread(() -> {
                try {
                    add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        for(int i=0;i<10;i++){
            new Thread(() -> {
                try {
                    remove();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

    static class testSynchornized{
        //生产者与消费者互斥使用仓库
        public static List<String> warehouse = new LinkedList<>();
        public static void main(String[] args) {
            //生产者线程
            Thread thread_1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized ( warehouse) {
                        int i = 0;
                        //生产10个商品
                        while(warehouse.size()<=10){
                            ++i;
                            //生产商品，添加进仓库
                            warehouse.add("生产了商品goods"+i);
                            //当商品数量足够时，便唤醒消费者线程
                            if(warehouse.size()>=10){
                                warehouse.notify();
                                //生产任务完成，跳出循环，结束运行，从而可以释放锁
                                break;
                            }
                        }
                    }
                }
            });

            //消费者线程
            Thread thread_2 = new Thread(){
                @Override
                public void run() {
                    synchronized (warehouse) {
                        try {//如果仓库的商品数量不能满足消费者
                            if(warehouse.size()<10){
                                //消费者进入等待队列，等待被唤醒
                                warehouse.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //消费商品
                        for(String goods:warehouse){
                            System.out.println("消费者消费了商品："+goods);
                        }
                    }
                }
            };
            //
            thread_2.start();
            thread_2.setPriority(Thread.MAX_PRIORITY);
            thread_1.start();
        }
    }


}






