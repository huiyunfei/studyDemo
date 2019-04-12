package com.example.threaddemo;

/**
 * 多线程模拟100人同时抢10张票
 * Created by huiyunfei on 2019/4/12.
 */
public class TicketThread implements Runnable {

    private int count=10;//剩余票数

    private int buyCount;//已购票数


    @Override
    public void run() {
        while (true){
            synchronized (this){
                if(count>0){
                    count--;
                    buyCount++;
                    System.out.println("当前剩余票数："+count+"第："+Thread.currentThread().getName()+"买到了第："+buyCount+"张票");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("已售完");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        TicketThread ticketThread=new TicketThread();
        for(int i=1;i<101;i++){
            new Thread(ticketThread).start();
        }
    }

}




















