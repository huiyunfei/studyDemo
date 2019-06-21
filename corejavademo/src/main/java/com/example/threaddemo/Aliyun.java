package com.example.threaddemo;

import lombok.Builder;

/**
 * Created by hui.yunfei@qq.com on 2019/6/20
 */
@Builder
public class Aliyun {

    private int endPoint;
    private String key;
    private String url;

    public int getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(int endPoint) {
        this.endPoint = endPoint;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public static void main(String[] args) {
        Aliyun builder=Aliyun.builder().endPoint(1).build();
        System.out.println(builder.getEndPoint());
    }
}












