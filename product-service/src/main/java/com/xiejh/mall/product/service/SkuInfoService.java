package com.xiejh.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiejh.common.utils.PageUtils;
import com.xiejh.mall.product.entity.SkuInfoEntity;

import java.util.Map;

/**
 * sku信息
 *
 * @author xiejh
 * @email 645236648@qq.com
 * @date 2020-10-29 21:44:33
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

