package com.xiejh.warehouse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiejh.common.utils.PageUtils;
import com.xiejh.warehouse.entity.WareSkuEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author xiejh
 * @email 645236648@qq.com
 * @date 2020-10-31 09:30:35
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Map<Long, Boolean> getSkusHasStock(List<Long> skuIds);
}

