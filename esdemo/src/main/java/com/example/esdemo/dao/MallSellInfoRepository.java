package com.example.esdemo.dao;

import com.example.esdemo.entity.MallSellInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MallSellInfoRepository extends ElasticsearchRepository<MallSellInfo, String> {

}
