package com.example.mybatisFB;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderInfoMapper {



    @TableSeg(shardBy = "orderId")
    List getDishByOrderId(@Param("orderId") String orderId);


    /**
     * 获得商家所有订单并分组
     *
     * @return
     */
    @TableSeg(shardBy = "begainTime,endTime")
    List findSellAllOrder(Map params);

}
