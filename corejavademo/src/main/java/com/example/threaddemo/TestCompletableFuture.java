package com.example.threaddemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


public class TestCompletableFuture {
    public static void main(String[] args) {
        Executor severs= Executors.newFixedThreadPool(10);
        List<CompletableFuture<Void>> futures = new ArrayList<CompletableFuture<Void>>();

        List list = new ArrayList();
        //计算出每个线程执行的数量
        int count=list.size()/9;
        for(int i=0;i<10;i++) {
            final List doList = new ArrayList();
            final AtomicInteger threadDoCount = new AtomicInteger();
            List threadList = new ArrayList<>();
            if (i == 9) {
                threadList = list.subList(i * count, list.size());
            } else {
                threadList = list.subList(i * count, (i + 1) * count);
            }
            List finalThreadList = threadList;
            futures.add(CompletableFuture.runAsync(() -> {
                try {
                    finalThreadList.forEach(obj->{
                        threadDoCount.getAndIncrement();
                        doList.add(obj);
                        //分批次去处理
                        if(doList.size()==1000){
                            //doList=matchMcc(doList, excelList);
                            //mybatis.update(doList);
                            doList.clear();
                        }
                    });
                    //剩余不足1000批量处理
                    if(doList!=null && doList.size()>0){
                        //doList=matchMcc(doList, excelList);
                        //mybatis.update(doList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, severs));
        }
        CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        allDoneFuture.join();
    }
}
