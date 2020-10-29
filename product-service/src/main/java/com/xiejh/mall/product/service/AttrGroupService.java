package com.xiejh.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiejh.common.utils.PageUtils;
import com.xiejh.mall.product.entity.AttrGroupEntity;

import java.util.Map;

/**
 * 属性分组
 *
 * @author xiejh
 * @email 645236648@qq.com
 * @date 2020-10-29 21:44:33
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

