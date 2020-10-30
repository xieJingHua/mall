package com.xiejh.product.dao;

import com.xiejh.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author xiejh
 * @email 645236648@qq.com
 * @date 2020-10-30 23:14:33
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
