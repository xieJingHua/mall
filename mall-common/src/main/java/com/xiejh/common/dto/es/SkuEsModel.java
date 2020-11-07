package com.xiejh.common.dto.es;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author xiejh
 * @Date 2020/11/7 10:43
 **/
@Data
public class SkuEsModel {

    private Long skuId;

    private Long spuId;

    private String skuTitle;

    private BigDecimal skuPrice;

    private String skuImg;

    private Long saleCount;

    private Boolean  hasStock;

    private Long  hotScore;

    private Long  brandId;

    private Long  catalogId;

    private String brandName;

    private String brandImg;

    private String catalogName;

    private List<Attr>  attrs;

    @Data
    public static   class Attr{
        private Long attrId;

        private String attrName;

        private String attrValue;

    }
}
