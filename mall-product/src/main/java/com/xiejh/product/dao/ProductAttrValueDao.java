package com.xiejh.product.dao;

import com.xiejh.product.entity.ProductAttrValueEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * spu属性值
 * 
 * @author xiejh
 * @email 645236648@qq.com
 * @date 2020-10-30 23:14:33
 */
@Mapper
public interface ProductAttrValueDao extends BaseMapper<ProductAttrValueEntity> {

    /**
     * 查询该spu所有可以检索的属性
     * @param spuId
     * @return
     */
    List<ProductAttrValueEntity> findSearchableList(@Param("spuId") Long spuId);
}
