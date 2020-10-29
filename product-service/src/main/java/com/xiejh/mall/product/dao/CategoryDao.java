package com.xiejh.mall.product.dao;

import com.xiejh.mall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author xiejh
 * @email 645236648@qq.com
 * @date 2020-10-29 21:44:33
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
