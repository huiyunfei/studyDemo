package com.example.esdemo.model;

import java.io.Serializable;

public class MallHomeParam implements Serializable {
    private static final long serialVersionUID = -4517506301768073323L;

    private String label;        //分类
    private String keyword;     //搜索关键词
    private Integer pageNow;    //页码，从0开始
    private Integer pageSize;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getPageNow() {
        return pageNow;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "MallHomeParam{" +
                "label='" + label + '\'' +
                ", keyword='" + keyword + '\'' +
                ", pageNow=" + pageNow +
                ", pageSize=" + pageSize +
                '}';
    }
}
