package com.xiejh.search.vo;

import com.xiejh.common.dto.es.SkuEsModel;
import lombok.Data;

import java.util.List;

/**
 * 搜索响应
 * @author xiejh
 * @Date 2020/11/9 22:46
 **/
@Data
public class SearchResult {
    //商品信息
    private List<SkuEsModel> products;

    /**
     * 以下是分页信息
     */
    private Integer pageNum; //当前页码
    private Long total;      //总记录数
    private Integer totalPages; //总页码
    //当前查询到的结果涉及的品牌
    private List<BrandVo>  brands;
    //当前查询到的结果，所涉及到的所有属性
    private List<AttrVo> attrs;
    //当前查询到的结果，所涉及到的所有分类
    private List<CategoryVo> categories;

    /**
     * 品牌
     */
    @Data
    public static class BrandVo{
        private Long brandId;
        private String brandName;
        private String brandImg;
    }

    /**
     * 属性
     */
    @Data
    public static class AttrVo{
        private Long attrId;
        private String attrName;
        private List<String> attrValues;
    }

    /**
     * 分类
     */
    @Data
    public static class CategoryVo{
        private Long categoryId;
        private String categoryName;
    }
}
