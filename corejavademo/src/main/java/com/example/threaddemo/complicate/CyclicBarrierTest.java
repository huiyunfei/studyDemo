package com.example.threaddemo.complicate;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 它允许一组线程相互等待直到所有线程都到达一个公共的屏障点。可理解为“人满发车”
 * Created by hui.yunfei@qq.com on 2019/6/21
 */
public class CyclicBarrierTest {
    //了解CyclicBarrier(parties)/getParties()/await()/getNumberWaiting()的基本用法。
    //理解循环的意义。
    static class GetParties {
        public static void main(String[] args) throws Exception {
            CyclicBarrier barrier0 = new CyclicBarrier(2);
            System.out.println("barrier.getParties()获取开启屏障的方数：" + barrier0.getParties());
            System.out.println("barrier.getParties()获取正在等待的线程数--初始化：" + barrier0.getNumberWaiting());
            new Thread(() -> {
                System.out.println("添加第一个线程：" + Thread.currentThread().getName());
                try {
                    barrier0.await();
                    System.out.println(Thread.currentThread().getName() + "is running.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " is end.");
            }).start();
            Thread.sleep(10);
            System.out.println("barrier.getParties()获取正在等待的线程数--添加第一个等待线程：" + barrier0.getNumberWaiting());
            Thread.sleep(10);
            new Thread(() -> {
                System.out.println("添加第二个线程：" + Thread.currentThread().getName());
                try {
                    barrier0.await();
                    System.out.println(Thread.currentThread().getName() + "is running.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " is end.");
            }).start();

            Thread.sleep(10);
            System.out.println("barrier.getParties()获取正在等待的线程数--打开屏障之后：" + barrier0.getNumberWaiting());
            Thread.sleep(10);

            new Thread(() -> {
                System.out.println("已经打开屏障后再次有第一个线程进入：" + Thread.currentThread().getName());
                try {
                    barrier0.await();
                    System.out.println(Thread.currentThread().getName() + "is running.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " is end.");
            }).start();
            Thread.sleep(10);
            System.out.println("barrier.getParties()获取正在等待的线程数--再次有第一个等待线程：" + barrier0.getNumberWaiting());
            Thread.sleep(10);
            new Thread(() -> {
                System.out.println("已经打开屏障后再次有第二个线程进入：" + Thread.currentThread().getName());
                try {
                    barrier0.await();
                    System.out.println(Thread.currentThread().getName() + "is running.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " is end.");
            }).start();
            Thread.sleep(10);
            System.out.println("barrier.getParties()获取正在等待的线程数--再次打开屏障：" + barrier0.getNumberWaiting());
            Thread.sleep(10);

        }

    }
    //熟悉reset()的用法
    //理解回归初始状态的意义
    static class Reset{
        public static void main(String[] args) throws Exception{

            CyclicBarrier barrier = new CyclicBarrier(2);
            barrier.reset();
            System.out.println("初始的CyclicBarrier执行Reset什么也不会发生");
            ExecutorService executorService = Executors.newCachedThreadPool();
            for(int i=0;i<2;i++){
                final int a=i;
                executorService.submit(()->{
                    try {
                        barrier.await();
                        System.out.println(a+"屏障已经打开.");
                    } catch (InterruptedException e) {
                        System.out.println(a+"屏障被中断.");
                    } catch (BrokenBarrierException e) {
                        System.out.println(a+"屏障被重置.");
                    }
                });
            }
            barrier.reset();
            Thread.sleep(100);
            System.out.println();
            //如果是一个 有线程正在等待的线程，则reset()方法会使正在等待的线程抛出异常
            executorService.submit(() -> {
                try {
                    barrier.await();
                    System.out.println("333屏障已经打开.");
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    System.out.println("333屏障被中断.");
                } catch (BrokenBarrierException e) {
                    System.out.println("在等待过程中，执行reset()方法，等待的线程抛出BrokenBarrierException异常，并不再等待");
                }
            });
            Thread.sleep(100);
            barrier.reset();
            executorService.shutdown();

        }
    }

//    练习await()/await(timeout,TimeUnit)/isBroken()的使用方法
//    理解破损标志位broken的状态转换
    static class aWait{
        public static void main(String[] args) throws Exception{
            CyclicBarrier barrier1 = new CyclicBarrier(3);
            ExecutorService executorService = Executors.newCachedThreadPool();
            //添加一个用await()等待的线程
            executorService.submit(() -> {
                try {
                    //等待，除非：1.屏障打开;2.本线程被interrupt;3.其他等待线程被interrupted;4.其他等待线程timeout;5.其他线程调用reset()
                    barrier1.await();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " is interrupted.");
                } catch (BrokenBarrierException e) {
                    System.out.println(Thread.currentThread().getName() + " is been broken.");
                }
            });
            Thread.sleep(10);
            System.out.println("刚开始，屏障是否破损：" + barrier1.isBroken());
            //添加一个等待线程-并超时
            executorService.submit(() -> {
                try {
                    //等待1s，除非：1.屏障打开(返回true);2.本线程被interrupt;3.本线程timeout;4.其他等待线程被interrupted;5.其他等待线程timeout;6.其他线程调用reset()
                    barrier1.await(1, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " is interrupted.");
                } catch (BrokenBarrierException e) {
                    System.out.println(Thread.currentThread().getName() + " is been reset().");
                } catch (TimeoutException e) {
                    System.out.println(Thread.currentThread().getName() + " is timeout.");
                }
            });
            Thread.sleep(100);
            System.out.println("当前等待线程数量：" + barrier1.getNumberWaiting());
            Thread.sleep(1000);
            System.out.println("当前等待线程数量：" + barrier1.getNumberWaiting());
            System.out.println("当等待的线程timeout时，当前屏障是否破损：" + barrier1.isBroken());
            System.out.println("等待的线程中，如果有一个出现问题，则此线程会抛出相应的异常；其他线程都会抛出BrokenBarrierException异常。");

            System.out.println();
            Thread.sleep(5000);
            //通过reset()重置屏障回初始状态，也包括是否破损
            barrier1.reset();
            System.out.println("reset()之后，当前屏障是否破损：" + barrier1.isBroken());
            System.out.println("reset()之后，当前等待线程数量：" + barrier1.getNumberWaiting());
            executorService.shutdown();
        }

    }


//    练习CyclicBarrier(int parties, Runnable barrierAction)的用法
//    理解屏障线程的意义
    static class CyclicConstractorTest{
        public static void main(String[] args) {
            //构造器：设置屏障放开前做的事情
            CyclicBarrier barrier3 = new CyclicBarrier(2, () -> {
                System.out.println("屏障放开，[屏障线程]先运行！");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("[屏障线程]的事情做完了!");
            });
            for (int i = 0; i < 2; i++) {
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + " 等待屏障放开");
                    try {
                        barrier3.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "开始干活...干活结束");
                }).start();
            }
        }

    }

//    模拟多线程分组计算
//    有一个大小为50000的随机数组，用5个线程分别计算10000个元素的和
//    然后在将计算结果进行合并，得出最后的结果。
    static class result{
        /**
         * <p>CyclicBarrier-循环屏障-模拟多线程计算</p>
         *
         * @author hanchao 2018/3/29 22:48
         **/
        public static void main(String[] args) {
            //数组大小
            int size = 50000;
            //定义数组
            int[] numbers = new int[size];
            //随机初始化数组
            for (int i = 0; i < size; i++) {
                numbers[i] = new Random().nextInt(100);
            }
    
            //单线程计算结果
            System.out.println();
            Long sum = 0L;
            for (int i = 0; i < size; i++) {
                sum += numbers[i];
            }
            System.out.println("单线程计算结果：" + sum);
    
            //多线程计算结果
            //定义线程池
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            //定义五个Future去保存子数组计算结果
            final int[] results = new int[5];
    
            //定义一个循环屏障，在屏障线程中进行计算结果合并
            CyclicBarrier barrier = new CyclicBarrier(5, () -> {
                int sums = 0;
                for (int i = 0; i < 5; i++) {
                    sums += results[i];
                }
                System.out.println("多线程计算结果：" + sums);
            });
    
            //子数组长度
            int length = 10000;
            //定义五个线程去计算
            for (int i = 0; i < 5; i++) {
                //定义子数组
                int[] subNumbers = Arrays.copyOfRange(numbers, (i * length), ((i + 1) * length));
                //盛放计算结果
                int finalI = i;
                executorService.submit(() -> {
                    for (int j = 0; j < subNumbers.length; j++) {
                        results[finalI] += subNumbers[j];
                    }
                    //等待其他线程进行计算
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                });
            }
    
            //关闭线程池
            executorService.shutdown();
        }
    }
}














