package com.example.forkjoin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

@Service
public class RiderOrderInitServiceImpl{

    private static Logger log = LoggerFactory.getLogger(RiderOrderInitServiceImpl.class);

    @Autowired
    @Qualifier("redisTemplate1")
    private RedisTemplate<String, Object> redisTemplate1;

    @Async
    public void initRiderOrder() {
        Long startTime=System.currentTimeMillis();
        Date minCreateTime = new Date();
        //Date minCreateTime = shopOrderMapper.getMinCreateTime();
        //Date currentDate = DateUtils.getBeforeTenDay(30);
        Date currentDate = new Date();
        List<RiderOrderInfoDTO> orderList = new ArrayList<>();
        //ForkJoin多线程处理工具类
        ForkJoinPool pool = new ForkJoinPool(4);
        RiderOrderForkUtils task = new RiderOrderForkUtils(minCreateTime, currentDate);
        Future<List<RiderOrderInfoDTO>> result = pool.submit(task);
        try {
            orderList = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }


}
