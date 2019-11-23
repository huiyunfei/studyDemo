package com.example.threaddemo.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


public class ThreadTest {
    private static Logger log = LoggerFactory.getLogger(ThreadTest.class);
    //线程数量
    final static int THREADCOUNT=10;
    final static ExecutorService service= Executors.newFixedThreadPool(THREADCOUNT);
    public void test(){
        //所有要处理的数据
        List dbList = new ArrayList();
        //excel导入对比数据
        List excelList=new ArrayList();
        CountDownLatch countDownLatch = new CountDownLatch(THREADCOUNT);
        //计算出每个线程执行的数量
        int count=dbList.size()/(THREADCOUNT-1);
        for(int i=0;i<THREADCOUNT;i++){
            final List doList = new ArrayList();
            final AtomicInteger threadDoCount = new AtomicInteger();
            //每个线程要处理的集合
            List threadList = new ArrayList<>();
            if(i==(THREADCOUNT-1)){
                threadList = dbList.subList(i*count,dbList.size());
            }else{
                threadList = dbList.subList(i*count,(i+1)*count);
            }
            List finalThreadList = threadList;
            int j=i+1;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    //计算当前所有用户的消费总额
                    try {
                        finalThreadList.forEach(obj->{
                            threadDoCount.getAndIncrement();
                            doList.add(obj);
                            //分批次去处理
                            if(doList.size()==100){
                                //doList=matchMcc(doList, excelList);
                                //mybatis.update(doList);
                                doList.clear();
                            }
                        });
                        //剩余不足100批量处理
                        if(doList!=null && doList.size()>0){
                            //doList=matchMcc(doList, excelList);
                            //mybatis.update(doList);
                        }
                    } catch (Exception e) {
                        log.error("批处理异常：{}",e.getMessage());
                    }finally {
                        log.info("线程：{}已执行完毕，分配数量：{}，执行的数量：{}，剩余被阻塞的线程数：{}",j,finalThreadList.size(),threadDoCount,countDownLatch.getCount());
                        countDownLatch.countDown();
                    }
                }
            });
        }

    }
}
