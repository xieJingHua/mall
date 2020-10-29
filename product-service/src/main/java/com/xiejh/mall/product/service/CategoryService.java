package com.xiejh.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiejh.common.utils.PageUtils;
import com.xiejh.mall.product.entity.CategoryEntity;

import java.util.Map;

/**
 * 商品三级分类
 *
 * @author xiejh
 * @email 645236648@qq.com
 * @date 2020-10-29 21:44:33
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

