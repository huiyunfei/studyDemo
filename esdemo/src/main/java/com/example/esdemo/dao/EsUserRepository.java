package com.example.esdemo.dao;

import com.example.esdemo.entity.EsUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Auther: ZMJ
 * @Date: 2019/6/3 15:39
 * @Description:
 */
public interface EsUserRepository extends ElasticsearchRepository<EsUser,String> {
    //Page<Object> findEsUsersByUsername(String username, Pageable pageable);
}
