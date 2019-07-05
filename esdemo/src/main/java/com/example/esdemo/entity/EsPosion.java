package com.example.esdemo.entity;

/**
 * @Auther: ZMJ
 * @Date: 2019/6/3 17:17
 * @Description:
 */
public class EsPosion {
private String properties;

private  String positionName;

    public EsPosion() {
    }

    public EsPosion(String properties, String positionName) {
        this.properties = properties;
        this.positionName = positionName;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
