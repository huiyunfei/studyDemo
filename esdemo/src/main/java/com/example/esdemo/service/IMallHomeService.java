package com.example.esdemo.service;


import com.example.esdemo.entity.MallSellInfo;
import com.example.esdemo.model.MallHomeParam;

import java.util.List;

public interface IMallHomeService {


    /**
     * 获取首页商家商品列表
     * @return
     */
    public List<MallSellInfo> listEsMallSell(MallHomeParam homeParam);
}
