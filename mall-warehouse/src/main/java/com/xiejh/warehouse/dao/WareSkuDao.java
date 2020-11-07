package com.xiejh.warehouse.dao;

import com.xiejh.warehouse.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 商品库存
 * 
 * @author xiejh
 * @email 645236648@qq.com
 * @date 2020-10-31 09:30:35
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {

    /**
     * 查看库存量
     * @param skuId
     * @return
     */
    Integer hasStock(Long skuId);
}
