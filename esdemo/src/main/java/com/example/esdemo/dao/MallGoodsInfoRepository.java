package com.example.esdemo.dao;

import com.example.esdemo.entity.MallGoodsInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MallGoodsInfoRepository extends ElasticsearchRepository<MallGoodsInfo, String> {

}
