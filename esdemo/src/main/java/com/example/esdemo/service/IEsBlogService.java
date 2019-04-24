package com.example.esdemo.service;

import com.example.esdemo.entity.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by hui.yunfei@qq.com on 2019/4/24
 */
public interface IEsBlogService {
    Page<EsBlog> getEsBlogByKeys(String keyword, Pageable pageable);
}
