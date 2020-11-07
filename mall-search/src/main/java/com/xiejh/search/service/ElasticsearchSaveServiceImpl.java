package com.xiejh.search.service;

import com.alibaba.fastjson.JSON;
import com.xiejh.common.dto.es.SkuEsModel;
import com.xiejh.search.config.ElasticsearchConfig;
import com.xiejh.search.constant.EsConstant;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author xiejh
 * @Date 2020/11/7 14:22
 **/
@Slf4j
@Service
public class ElasticsearchSaveServiceImpl implements ElasticsearchSaveService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public boolean productUp(List<SkuEsModel> skuEsModels) throws IOException {
        //1.给es建好索引，以及字段类型映射关系


        //2.批量保存
        BulkRequest bulkRequest = new BulkRequest();
        skuEsModels.forEach(e->{
            IndexRequest indexRequest = new IndexRequest();
            indexRequest.index(EsConstant.PRODUCT_INDEX);
            indexRequest.id(e.getSkuId().toString());
            indexRequest.source(JSON.toJSONString(e), XContentType.JSON);
            bulkRequest.add(indexRequest);
        });
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, ElasticsearchConfig.COMMON_OPTIONS);

        //如果有错误
       if(bulk.hasFailures()){
           log.error("商品上架错误,{}",bulk.getItems());
       }
       return bulk.hasFailures();
    }
}
