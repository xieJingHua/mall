package com.xiejh.search;

import com.alibaba.fastjson.JSON;
import com.xiejh.search.config.ElasticsearchConfig;
import lombok.Data;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;

@SpringBootTest
class MallSearchApplicationTests {

    @Autowired
    private RestHighLevelClient esClient;

    @Test
    void testEs() throws IOException {
        IndexRequest request = new IndexRequest();
        request.index("user");
        User user = new User();
        user.setName("张三");
        user.setAge(18);
        request.source(JSON.toJSONString(user), XContentType.JSON);
        IndexResponse response = esClient.index(request, ElasticsearchConfig.COMMON_OPTIONS);
        System.out.println(request);
    }

    @Data
    class  User{
        private String name;
        private Integer age;
    }

    @Test
    void contextLoads() {
        System.out.println(esClient);
    }


    @Test
    void search() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("address","mill"));


        //按照年龄的值分布进行聚合，其实就是按年龄分组统计
        TermsAggregationBuilder ageAgg = AggregationBuilders.terms("ageAgg").field("age").size(10);
        searchSourceBuilder.aggregation(ageAgg);

        //计算平均薪资
        AvgAggregationBuilder balanceAvg = AggregationBuilders.avg("balanceAvg").field("balance");
        searchSourceBuilder.aggregation(balanceAvg);

        searchRequest.source(searchSourceBuilder);
        System.out.println(searchRequest.toString());
        SearchResponse response = esClient.search(searchRequest, ElasticsearchConfig.COMMON_OPTIONS);
        System.out.println("======");
        System.out.println(response);
        SearchHits hits = response.getHits();
        TotalHits totalHits = hits.getTotalHits();
        System.out.println("总数："+totalHits);
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit hit : hits1) {
            System.out.println("文档："+hit.getSourceAsString());
        }
        Aggregations aggregations = response.getAggregations();
        Terms ageAgg1 = aggregations.get("ageAgg");
        for (Terms.Bucket bucket : ageAgg1.getBuckets()) {
            System.out.println("年龄："+bucket.getKey()+",总数："+bucket.getDocCount());
        }
        Avg avg = aggregations.get("balanceAvg");
        System.out.println("薪资平均："+avg.getValue());
    }
}
