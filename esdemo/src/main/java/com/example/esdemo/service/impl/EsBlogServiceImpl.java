package com.example.esdemo.service.impl;

import com.example.esdemo.dao.IEsBlogRepository;
import com.example.esdemo.entity.EsBlog;
import com.example.esdemo.service.IEsBlogService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

/**
 * Created by hui.yunfei@qq.com on 2019/4/24
 */
@Service
public class EsBlogServiceImpl implements IEsBlogService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private IEsBlogRepository esBlogRepository;
    @Override
    public Page<EsBlog> getEsBlogByKeys(String keyword, Pageable pageable) {
        Sort sort = new Sort(Sort.Direction.DESC,"read_size","comment_size","like_size");
        if (pageable.getSort() == null) {
            pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
        }
        if(keyword==null || "".equals(keyword)){
            return esBlogRepository.findAll(pageable);
        }
        //keyword 含有空格时抛异常
        //return esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(keyword, keyword, keyword, keyword, pageable);

        //使用 Elasticsearch API QueryBuilder
        NativeSearchQueryBuilder aNativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        aNativeSearchQueryBuilder.withIndices("sl_blog").withTypes("doc");
        final BoolQueryBuilder aQuery = new BoolQueryBuilder();
        //builder下有的must、should、mustNot 相当于逻辑运算and、or、not
        aQuery.should(QueryBuilders.queryStringQuery(keyword).defaultField("title"));
        aQuery.should(QueryBuilders.queryStringQuery(keyword).defaultField("content"));

        NativeSearchQuery nativeSearchQuery = aNativeSearchQueryBuilder.withQuery(aQuery).build();
        Page<EsBlog> plist = elasticsearchTemplate.queryForPage(nativeSearchQuery,EsBlog.class);
        return  plist;
    }
}
