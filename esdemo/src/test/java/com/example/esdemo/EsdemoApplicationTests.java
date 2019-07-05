package com.example.esdemo;

import com.example.esdemo.dao.EsUserRepository;
import com.example.esdemo.entity.EsPosion;
import com.example.esdemo.entity.EsUser;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsdemoApplicationTests {


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private EsUserRepository esUserRepository;
    @Before
    public void initRepositoryData(){
        //清除ES中数据以免影响测试结果
        List<EsPosion> posionName=new ArrayList<EsPosion>();
        posionName.add(new EsPosion("操作工","普工"));
        posionName.add(new EsPosion("操作工","汽车维修"));
        esUserRepository.index(new EsUser("3","张运昌","男","1990-05-06",30,posionName));
        //esUserRepository.save(new EsUser("4","小龙","bmn",1,classList));

    }
    @Test
    public void contextLoads() {
        Pageable pageable = PageRequest.of(0,3);
        /*NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();*/
        //结果过滤 ctrl + p 可以看需要传入的参数类型 第一个数组类型是需要包含的  第二个参数是不包含的
        //queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{"id","title","price"},null));
        //添加查询条件


       /* QueryBuilder orderItemsQuery = QueryBuilders.nestedQuery("intentionPosition",
                QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("intentionPosition.positionName", "普工"))
                       ,
                ScoreMode.Total);*/


        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        /**按照区间搜索**/
        BoolQueryBuilder boo=  boolQueryBuilder.must(QueryBuilders.rangeQuery("bornInteger").gte(20)).
                must(QueryBuilders.rangeQuery("bornInteger").lte(25));
        /**搜索子类数据**/
        //BoolQueryBuilder boo=  boolQueryBuilder.must(QueryBuilders.matchQuery("intentionPosition.positionName","普"));
        //NativeSearchQueryBuilder query = queryBuilder.withQuery(QueryBuilders.rangeQuery("bornInteger").lte(20));
        //MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("title", "小米手机");
        //
        Iterable<EsUser> page = esUserRepository.search(boo);
        //Page<Object> result = esUserRepository.findEsUsersByUsername("fanqi",pageable);
        //assertThat(result.getTotalElements()).isEqualTo(3);
        System.out.println("-----开始遍历结果数据-----");
      /*  for (EsUser user:result.getContent()){
            System.out.println(user.toString());
        }*/
    }

    @Test
    public void deleteIndex(){
        //esUserRepository.delete(new EsUser());
        elasticsearchTemplate.deleteIndex(EsUser.class);
    }
}
