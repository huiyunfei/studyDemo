package com.example.esdemo.web;

import com.example.esdemo.entity.MallSellInfo;
import com.example.esdemo.model.MallHomeParam;
import com.example.esdemo.service.IMallHomeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hui.yunfei@qq.com on 2019/8/20
 */

@RestController
public class EsSearchController {

    private static final Logger log = LoggerFactory.getLogger(EsSearchController.class);


    @Autowired
    private IMallHomeService mallHomeService;

    @RequestMapping("/search")
    public Map search(@RequestBody MallHomeParam homeParam) {
        Map result = new HashMap();

        try {
            if (homeParam.getPageNow() == null || homeParam.getPageSize() == null || StringUtils.isBlank(homeParam.getKeyword())) {
                log.error("============/mall/home/seach:");
            }

            List<MallSellInfo> sellInfos = mallHomeService.listEsMallSell(homeParam);

        } catch (Exception e) {
            log.error("============/mall/home/seach:", e);
        }

        return result;
    }
}
