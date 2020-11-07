package com.xiejh.search.service;

import com.xiejh.common.dto.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

/**
 * @author xiejh
 * @Date 2020/11/7 14:22
 **/
public interface ElasticsearchSaveService {
    boolean productUp(List<SkuEsModel> skuEsModels) throws IOException;
}
