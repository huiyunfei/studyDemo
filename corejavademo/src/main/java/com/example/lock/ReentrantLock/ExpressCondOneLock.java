package com.example.lock.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *类说明：
 */
public class ExpressCondOneLock {
    public final static String CITY = "ShangHai";
    private int km;/*快递运输里程数*/
    private String site;/*快递到达地点*/
    private Lock lock = new ReentrantLock();
    private Condition kmCond = lock.newCondition();
    private Condition siteCond = lock.newCondition();

    public ExpressCondOneLock() {
    }

    public ExpressCondOneLock(int km, String site) {
        this.km = km;
        this.site = site;
    }

    /* 变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理*/
    public void changeKm(){
		lock.lock();
    	try {
    		this.km = 101;
    		kmCond.signal();//通知其他在锁上等待的线程
    	}finally {
			lock.unlock();
    	}
        
        
    }

    /* 变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理*/
    public  void changeSite(){
		lock.lock();
    	try {
    		this.site = "BeiJing";
    		siteCond.signal();//通知其他在锁上等待的线程
    	}finally {
			lock.unlock();
    	}    	
    }

    /*当快递的里程数大于100时更新数据库*/
    public void waitKm(){
		lock.lock();
    	try {
        	while(this.km<100) {
        		try {
        			kmCond.await();//当前线程进行等待
    				System.out.println("check km thread["+Thread.currentThread().getName()
    						+"] is be notify");
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	}    		
    	}finally {
			lock.unlock();
    	}

        System.out.println("the Km is "+this.km+",I will change db");
    }

    /*当快递到达目的地时通知用户*/
    public void waitSite(){
		lock.lock();
    	try {
        	while(this.site.equals(CITY)) {
        		try {
    				siteCond.await();//当前线程进行等待
    				System.out.println("check Site thread["+Thread.currentThread().getName()
    						+"] is be notify");
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}   		
        	}
    	}finally {
			lock.unlock();
    	}      	

        System.out.println("the site is "+this.site+",I will call user");
    }
}
