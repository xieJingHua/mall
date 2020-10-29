package com.xiejh.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiejh.common.utils.PageUtils;
import com.xiejh.mall.product.entity.AttrAttrgroupRelationEntity;

import java.util.Map;

/**
 * 属性&属性分组关联
 *
 * @author xiejh
 * @email 645236648@qq.com
 * @date 2020-10-29 21:44:33
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

