package com.xiejh.search.controller;

import com.xiejh.common.dto.es.SkuEsModel;
import com.xiejh.common.exception.ResponseCode;
import com.xiejh.common.utils.R;
import com.xiejh.search.service.ElasticsearchSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author xiejh
 * @Date 2020/11/7 14:21
 **/
@Slf4j
@RestController
@RequestMapping("/search/save")
public class ElasticsearchSaveController {

    @Autowired
    private ElasticsearchSaveService elasticsearchSaveService;

    /**
     * 商品上架
     * @param skuEsModels
     * @return
     */
    @PostMapping("/productUp")
    public R productUp(@RequestBody List<SkuEsModel> skuEsModels){
        boolean success = false;
        try {
            success = elasticsearchSaveService.productUp(skuEsModels);
        } catch (IOException e) {
            log.error("商品上架出现异常",e);
            return R.error(ResponseCode.PRODUCT_UP_EXCEPTION.getCode(), ResponseCode.PRODUCT_UP_EXCEPTION.getMsg());
        }
        if(!success)
            return R.error(ResponseCode.PRODUCT_UP_EXCEPTION.getCode(), ResponseCode.PRODUCT_UP_EXCEPTION.getMsg());
        return R.ok();
    }
}
