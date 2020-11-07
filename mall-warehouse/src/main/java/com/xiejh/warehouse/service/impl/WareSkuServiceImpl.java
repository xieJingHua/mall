package com.xiejh.warehouse.service.impl;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiejh.common.utils.PageUtils;
import com.xiejh.common.utils.Query;

import com.xiejh.warehouse.dao.WareSkuDao;
import com.xiejh.warehouse.entity.WareSkuEntity;
import com.xiejh.warehouse.service.WareSkuService;


@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                new QueryWrapper<WareSkuEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Map<Long, Boolean> getSkusHasStock(List<Long> skuIds) {
        Map<Long, Boolean> result = skuIds.stream().map(skuId -> {
            Map<String, Object> stock = new HashMap<>(2);
            stock.put("skuId", skuId);
            Integer stockNum = this.baseMapper.hasStock(skuId);
            stock.put("hasStock", stockNum > 0 ? true : false);
            return stock;
        }).collect(Collectors.toMap(e -> (Long) e.get("skuId"), e -> (Boolean) e.get("hasStock")));
        return result;
    }

}