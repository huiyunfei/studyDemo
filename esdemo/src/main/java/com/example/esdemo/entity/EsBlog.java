package com.example.esdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * Created by hui.yunfei@qq.com on 2019/4/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//映射Elasticsearch中的索引和文档类型
@Document(indexName = "index_blog", type = "doc")//, type = "blog" 7以后type已经删了
public class EsBlog {

    @Id
    private String id;

    private String title;

    private Date createTime;

    private String content;


}










