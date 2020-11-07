package com.xiejh.product.service.impl;

import com.xiejh.common.dto.es.SkuEsModel;
import com.xiejh.common.utils.R;
import com.xiejh.product.entity.*;
import com.xiejh.product.feign.SearchFeignService;
import com.xiejh.product.feign.WareFeignService;
import com.xiejh.product.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiejh.common.utils.PageUtils;
import com.xiejh.common.utils.Query;

import com.xiejh.product.dao.SpuInfoDao;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    private SkuInfoService skuInfoService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService  categoryService;
    @Autowired
    private ProductAttrValueService productAttrValueService;
    @Autowired
    private WareFeignService wareFeignService;
    @Autowired
    private SearchFeignService searchFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void up(Long spuId) {
        List<SkuEsModel> upProducts = new ArrayList<>();

        List<SkuInfoEntity> skus = skuInfoService.getSkuBySpuId(spuId);

        //查询当前sku所有可以用来检索的规格属性
        List<ProductAttrValueEntity> attrValues = productAttrValueService.findSearchableList(spuId);
        List<SkuEsModel.Attr> attrs = attrValues.stream().map(e -> {
            SkuEsModel.Attr attr = new SkuEsModel.Attr();
            BeanUtils.copyProperties(e, attr);
            return attr;
        }).collect(Collectors.toList());

        //发送远程调用，库存系统查询是否有库存
        List<Long> skuIds = skus.stream().map(e -> e.getSkuId()).collect(Collectors.toList());
        R<Map<Long, Boolean>> skusHasStockResult = wareFeignService.getSkusHasStock(skuIds);
        Map<Long, Boolean> stockMap = skusHasStockResult.getData();

        List<SkuEsModel> skuModels = skus.stream().map(e -> {
            SkuEsModel skuEsModel = new SkuEsModel();
            BeanUtils.copyProperties(e,skuEsModel);
            skuEsModel.setSkuPrice(e.getPrice());
            skuEsModel.setSkuImg(e.getSkuDefaultImg());
            //库存
            skuEsModel.setHasStock(stockMap.get(e.getSkuId()));

            //热度评分,默认0
            skuEsModel.setHotScore(0L);

            //查询品牌和分类的名字信息
            BrandEntity brand = brandService.getById(e.getBrandId());
            skuEsModel.setBrandName(brand.getName());
            skuEsModel.setBrandImg(brand.getLogo());
            CategoryEntity category = categoryService.getById(e.getCatalogId());
            skuEsModel.setCatalogName(category.getName());

            skuEsModel.setAttrs(attrs);
            return skuEsModel;
        }).collect(Collectors.toList());

        //发送给es保存
        R result = searchFeignService.productUp(upProducts);
        if(result.getCode()==0){
            //更新为商品已上架
            SpuInfoEntity spu = new SpuInfoEntity();
            spu.setId(spuId);
            spu.setPublishStatus(1);
            this.baseMapper.updateById(spu);
        }
    }

}