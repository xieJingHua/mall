package com.xiejh.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiejh.common.utils.PageUtils;
import com.xiejh.mall.product.entity.AttrEntity;

import java.util.Map;

/**
 * 商品属性
 *
 * @author xiejh
 * @email 645236648@qq.com
 * @date 2020-10-29 21:44:33
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

