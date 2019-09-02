package com.example.lock.aqs;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hui.yunfei@qq.com on 2019/8/21
 */
public class LockDemo {

    public LockDemo(String[] arr) {
        this.arr = arr;
    }

    Lock lock = new ReentrantLock();
    Condition removeCondition=lock.newCondition();
    Condition addCondition=lock.newCondition();

    private volatile int j=0;
    String[] arr=new String[]{};

    public void add() {
        lock.lock();
        try {
            if(arr==null){
                arr = new String[5];
                int s=new Random().nextInt(9);
                arr[j] = s+"";
                System.out.println("新增一个元素："+Arrays.toString(arr));
                j++;
                //通知可以移除元素
                removeCondition.signal();
            }else{
                if(arr.length<5){
                    int s=new Random().nextInt(9);
                    j++;
                    arr[j] = s+"";
                    System.out.println("新增一个元素："+Arrays.toString(arr));
                    //通知可以移除元素
                    removeCondition.signal();
                }else{
                    addCondition.await();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void remove() {
        lock.lock();
        try {
            if(arr!=null && arr.length!=5){
                arr=new String[5];
                System.out.println("移除一个元素："+ Arrays.toString(arr));
                //通知可以添加元素
                addCondition.signal();
            }else{
                removeCondition.await();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public static class TestLock {
        public static LockDemo  demo = new LockDemo(null);
        private static class doAdd extends Thread{
            @Override
            public void run() {
                demo.add();
            }
        }

        private static class doRemove extends Thread{
            @Override
            public void run() {
                demo.remove();
            }
        }
        public static void main(String[] args) throws Exception{
            for(int i=0;i<5;i++){
                new doAdd().start();
                new doRemove().start();
                Thread.sleep(200);
            }
        }
    }

}





