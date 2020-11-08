package com.xiejh.product.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiejh.common.utils.PageUtils;
import com.xiejh.common.utils.Query;

import com.xiejh.product.dao.SkuInfoDao;
import com.xiejh.product.entity.SkuInfoEntity;
import com.xiejh.product.service.SkuInfoService;


@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private Object lock = new Object();

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<SkuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<SkuInfoEntity> getSkuBySpuId(Long spuId) {
        List<SkuInfoEntity> list = this.list(new QueryWrapper<SkuInfoEntity>().eq("spu_id", spuId));
        return list;
    }

    /**
     * 1.空结果缓存，解决缓存穿透（查不存在的数据,请求打到db）
     * 2.设置过期时间（加随机值）,解决缓存雪崩(缓存同时大面积失效，所有请求落到db)
     * 3.加锁，解决缓存击穿
     * @param skuId
     * @return
     */
    @Override
    public SkuInfoEntity getBySkuId(Long skuId) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String json = ops.get("sku_"+skuId);
        if(StringUtils.isBlank(json)){
            //加锁放缓存击穿，double check
            synchronized (lock){
                json = ops.get("sku_"+skuId);
                if(StringUtils.isNotBlank(json)){
                    return JSON.parseObject(json,SkuInfoEntity.class);
                }
                SkuInfoEntity skuInfoEntity = this.baseMapper.selectById(skuId);
                //过期时间加随机值，防缓存雪崩
                //空也存，防缓存穿透
                ops.set("sku_"+skuId, JSON.toJSONString(skuInfoEntity),1, TimeUnit.DAYS);
                return skuInfoEntity;
            }
        }
        return JSON.parseObject(json,SkuInfoEntity.class);
    }

}