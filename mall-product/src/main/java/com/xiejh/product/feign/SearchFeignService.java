package com.xiejh.product.feign;

import com.xiejh.common.dto.es.SkuEsModel;
import com.xiejh.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author xiejh
 * @Date 2020/11/7 15:02
 **/
@FeignClient("mall-search")
public interface SearchFeignService {

    /**
     * 商品上架
     *
     * @param skuEsModels
     * @return
     */
    @PostMapping("/search/save/productUp")
    R productUp(@RequestBody List<SkuEsModel> skuEsModels);
}
