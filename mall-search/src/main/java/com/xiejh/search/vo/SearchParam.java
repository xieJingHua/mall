package com.xiejh.search.vo;

import java.util.List;

/**
 * @author xiejh
 * @Date 2020/11/9 20:46
 **/
public class SearchParam {
    //关键字
    private String keyword;
    //三级分类id
    private Long category3Id;
    //排序条件
    private String sort;
    //是否只显示有货
    private Integer hasStock;
    //价格区间查询
    private String skuPrice;
    //品牌，多选
    private List<Long> brandIds;
    //属性
    private List<String> attrs;
    //页码
    private Integer pageNum;
}
