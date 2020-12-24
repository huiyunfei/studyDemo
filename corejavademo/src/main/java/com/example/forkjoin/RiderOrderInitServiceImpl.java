package com.example.forkjoin;

import com.alibaba.fastjson.JSON;
import com.fhgl.middleware.constant.RedisConstants;
import com.fhgl.middleware.mapper.ShopOrderMapper;
import com.fhgl.middleware.modal.RiderOrderInfoDTO;
import com.fhgl.middleware.service.inf.IRiderOrderInitService;
import com.fhgl.middleware.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
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
public class RiderOrderInitServiceImpl implements IRiderOrderInitService {

    private static Logger log = LoggerFactory.getLogger(RiderOrderInitServiceImpl.class);

    @Autowired
    private ShopOrderMapper shopOrderMapper;

    @Autowired
    @Qualifier("redisTemplate1")
    private RedisTemplate<String, Object> redisTemplate1;

    @Async
    @Override
    public void initRiderOrder() {
        Long startTime=System.currentTimeMillis();
        Date minCreateTime = shopOrderMapper.getMinCreateTime();
        Date currentDate = DateUtils.getBeforeTenDay(30);
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
        redisTemplate1.opsForValue().set(RedisConstants.RIDER_ORDERTIME, DateUtils.dateToStrLong(currentDate));
        List<RiderOrderInfoDTO> finalOrderList = orderList;
        List<Object> resultList = redisTemplate1.executePipelined(new RedisCallback<Object>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();
                finalOrderList.forEach(e -> {
                    connection.set((RedisConstants.RIDER_ORDERINFO + e.getRiderId()).getBytes(), JSON.toJSONString(e).getBytes());
                });
                return null;
            }
        });
        Long endTime=System.currentTimeMillis();
        log.info("初始化骑手订单量结束,总时长为:{}",endTime-startTime);


    }


}
