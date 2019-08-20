package com.example.esdemo.service.impl;

import com.example.esdemo.dao.MallGoodsInfoRepository;
import com.example.esdemo.entity.MallGoodsInfo;
import com.example.esdemo.entity.MallSellInfo;
import com.example.esdemo.model.MallHomeParam;
import com.example.esdemo.service.IMallHomeService;
import com.example.esdemo.utils.ChineseEnglishUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.HasChildQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.support.QueryInnerHitBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class MallHomeServiceImpl implements IMallHomeService {

    private static final Logger log = LoggerFactory.getLogger(MallHomeServiceImpl.class);

    @Autowired
    private Client client;

    @Autowired
    private MallGoodsInfoRepository mallGoodsInfoRepository;

    private static final String PARENT_ID = "parentId";
    private static final String PARENT_NAME = "parentName";

    private static final String PINYIN = ".my_pinyin";
    private static final String IK_MAX_WORD = "ik_max_word";

    /**
     * 首页商品显示个数
     */
    private static Integer GOODS_SHOW_COUNT_HOME = 3;

    /**
     * 所有商品显示个数
     */
    private static Integer GOODS_SHOW_COUNT_ALL = 50;

    @Override
    public List<MallSellInfo> listEsMallSell(MallHomeParam homeParam) {
        try {
            QueryBuilder searchQuery = generateEsSearchConditions(homeParam);

            // 店铺名升序排序
            SortBuilder sellNameBuilder = new FieldSortBuilder("sellName");
            sellNameBuilder.order(SortOrder.ASC);

            log.info(searchQuery.toString());

            SearchResponse response = client.prepareSearch("mall").setTypes("sell")
                    .setQuery(searchQuery).setFrom(homeParam.getPageNow() * homeParam.getPageSize()).setSize(homeParam.getPageSize())
                    .addSort(sellNameBuilder).execute().actionGet();

            List<MallSellInfo> mallSellInfos = new LinkedList<>();

            if(response != null) {

                for (SearchHit hit : response.getHits()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    MallSellInfo sellInfo = objectMapper.readValue(hit.getSourceAsString(), MallSellInfo.class);

                    //商品信息
                    Map<String, SearchHits> inner_hits = hit.getInnerHits();
                    SearchHits childs = inner_hits.get("goods");

                    if (childs != null) {
                        List<MallGoodsInfo> goodsInfos = new LinkedList<>();
                        for (SearchHit dishHit : childs.getHits()) {
                            objectMapper = new ObjectMapper();
                            MallGoodsInfo goodsInfo = objectMapper.readValue(dishHit.getSourceAsString(), MallGoodsInfo.class);
                            goodsInfos.add(goodsInfo);
                        }

                        if (StringUtils.isNotBlank(homeParam.getKeyword()) && goodsInfos.isEmpty()) {
                            NativeSearchQueryBuilder goodsBuilder = new NativeSearchQueryBuilder();

                            BoolQueryBuilder goodsBoolQueryBuilder = new BoolQueryBuilder();
                            goodsBoolQueryBuilder.must(QueryBuilders.termQuery(PARENT_ID, sellInfo.getId()));

                            BoolQueryBuilder examineBuilder = new BoolQueryBuilder();

                            BoolQueryBuilder notExistBuilder = new BoolQueryBuilder();
                            notExistBuilder.mustNot(QueryBuilders.existsQuery("examineStatus"));
                            examineBuilder.should(notExistBuilder);
                            examineBuilder.should(QueryBuilders.termQuery("examineStatus", 2));

                            goodsBoolQueryBuilder.must(QueryBuilders.termQuery("goodsStatus", 0)).must(QueryBuilders.termQuery("isDelete", 1)).must(examineBuilder);
                            goodsBuilder.withQuery(goodsBoolQueryBuilder);

                            SortBuilder salesCountBuilder = new FieldSortBuilder("monthGoodsAmount");
                            salesCountBuilder.order(SortOrder.DESC);
                            goodsBuilder.withSort(salesCountBuilder);
                            goodsBuilder.withPageable(new PageRequest(0, GOODS_SHOW_COUNT_ALL));

                            SearchQuery goodsSearchQuery = goodsBuilder.build();

                            Iterable<MallGoodsInfo> goodsInfoIterable = mallGoodsInfoRepository.search(goodsSearchQuery);
                            goodsInfos = Lists.newArrayList(goodsInfoIterable.iterator());
                        }

                        sellInfo.setGoodsList(goodsInfos);
                    }

                    mallSellInfos.add(sellInfo);
                }
            }

            return mallSellInfos;
        } catch (Exception e) {
            log.error("查询首页接口异常" ,e);
            return new LinkedList();
        }
    }

    public QueryBuilder generateEsSearchConditions(MallHomeParam homeParam) {
        // 实现了SearchQuery接口，用于组装QueryBuilder和SortBuilder以及Pageable等
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();

        queryBuilder.must(QueryBuilders.termQuery("sellStatus", 0));
        queryBuilder.must(QueryBuilders.termQuery("sellUserStatus", 0));

        /*
         * 查询子集goods
         */
        BoolQueryBuilder goodsBuilder = new BoolQueryBuilder();

        BoolQueryBuilder examineBuilder = new BoolQueryBuilder();
        BoolQueryBuilder notExistBuilder = new BoolQueryBuilder();

        notExistBuilder.mustNot(QueryBuilders.existsQuery("examineStatus"));

        examineBuilder.should(notExistBuilder);
        examineBuilder.should(QueryBuilders.termQuery("examineStatus", 2));

        goodsBuilder.must(QueryBuilders.termQuery("goodsStatus", 0));
        goodsBuilder.must(QueryBuilders.termQuery("isDelete", 1));

        //分类标签
        if (StringUtils.isNotBlank(homeParam.getLabel()) && !StringUtils.equals(homeParam.getLabel(), "全部")) {
            goodsBuilder.must(QueryBuilders.matchPhrasePrefixQuery("sellLabel", homeParam.getLabel()));
        }

        //搜索关键字
        String keyword = homeParam.getKeyword();
        if (StringUtils.isNotBlank(keyword)) {
            BoolQueryBuilder keywordBuilder = new BoolQueryBuilder();

            //店铺名、商品名匹配关键字
            if(ChineseEnglishUtils.isEnglish(keyword)) {	//拼音或英语搜索
                keywordBuilder.should(QueryBuilders.matchQuery("goodsName" + PINYIN, keyword));
                keywordBuilder.should(QueryBuilders.matchPhrasePrefixQuery("goodsName", keyword).analyzer(IK_MAX_WORD));

                keywordBuilder.should(QueryBuilders.matchQuery(PARENT_NAME + PINYIN, keyword));
                keywordBuilder.should(QueryBuilders.matchPhrasePrefixQuery(PARENT_NAME, keyword).analyzer(IK_MAX_WORD));
            } else {	//汉字搜索
                keywordBuilder.should(QueryBuilders.matchPhrasePrefixQuery("goodsName", keyword).analyzer(IK_MAX_WORD));
                keywordBuilder.should(QueryBuilders.matchPhrasePrefixQuery(PARENT_NAME, keyword).analyzer(IK_MAX_WORD));
            }

            goodsBuilder.must(keywordBuilder);
        } else {
            //首页显示顺序
            goodsBuilder.must(QueryBuilders.rangeQuery("homeSequence").gt(0));
        }

        //菜品type子查询
        HasChildQueryBuilder hasChildQueryBuilder = new HasChildQueryBuilder("goods", goodsBuilder);

        QueryInnerHitBuilder queryInnerHitBuilder = new QueryInnerHitBuilder();
        if(StringUtils.isNotBlank(homeParam.getKeyword())) {
            queryInnerHitBuilder.setSize(GOODS_SHOW_COUNT_ALL);
        } else {
            queryInnerHitBuilder.setSize(GOODS_SHOW_COUNT_HOME);
        }

        queryInnerHitBuilder.addSort("homeSequence", SortOrder.ASC);

        hasChildQueryBuilder.innerHit(queryInnerHitBuilder);
        queryBuilder.must(hasChildQueryBuilder);

        builder.withQuery(queryBuilder);
        SearchQuery searchQuery = builder.build();

        return searchQuery.getQuery();
    }
}
