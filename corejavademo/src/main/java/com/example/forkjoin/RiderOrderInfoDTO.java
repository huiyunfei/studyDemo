package com.example.forkjoin;

public class RiderOrderInfoDTO {
    private Integer riderId;

    private Integer orderCount;



    public RiderOrderInfoDTO(){

    }

    public RiderOrderInfoDTO(Integer riderId, Integer orderCount) {
        this.riderId = riderId;
        this.orderCount = orderCount;
    }

    public Integer getRiderId() {
        return riderId;
    }

    public void setRiderId(Integer riderId) {
        this.riderId = riderId;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }


}
