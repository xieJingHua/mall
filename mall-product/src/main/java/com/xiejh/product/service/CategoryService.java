package com.xiejh.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiejh.common.utils.PageUtils;
import com.xiejh.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author xiejh
 * @email 645236648@qq.com
 * @date 2020-10-30 23:14:33
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询树形结构数据
     * @return
     */
    List<CategoryEntity> listTree();
}

