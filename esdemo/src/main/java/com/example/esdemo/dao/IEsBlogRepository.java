package com.example.esdemo.dao;

import com.example.esdemo.entity.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Component;

/**
 * Created by hui.yunfei@qq.com on 2019/4/24
 */
@Component
public interface IEsBlogRepository extends ElasticsearchCrudRepository<EsBlog,String> {

    Page<EsBlog> findDistinctEsBlogByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}




