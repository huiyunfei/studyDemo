package com.example.esdemo.dao;

import com.example.esdemo.entity.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * Created by hui.yunfei@qq.com on 2019/4/24
 */
@Component
public interface IEsBlogRepository extends ElasticsearchRepository<EsBlog,String> {

    Page<EsBlog> findDistinctEsBlogByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}




