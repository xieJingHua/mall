package com.xiejh.product.feign;

import com.xiejh.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @author xiejh
 * @Date 2020/11/7 13:54
 **/
@FeignClient("mall-warehouse")
public interface WareFeignService {

    /**
     * 查看sku是否有库存
     *
     * @param skuIds
     * @return
     */
    @PostMapping("/warehouse/waresku/hasstock")
    R<Map<Long, Boolean>> getSkusHasStock(@RequestBody List<Long> skuIds);
}
