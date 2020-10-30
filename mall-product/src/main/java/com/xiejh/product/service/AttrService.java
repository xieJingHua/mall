package com.xiejh.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiejh.common.utils.PageUtils;
import com.xiejh.product.entity.AttrEntity;

import java.util.Map;

/**
 * 商品属性
 *
 * @author xiejh
 * @email 645236648@qq.com
 * @date 2020-10-30 23:14:33
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

