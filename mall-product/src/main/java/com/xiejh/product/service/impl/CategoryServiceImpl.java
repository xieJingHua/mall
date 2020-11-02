package com.xiejh.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiejh.common.utils.PageUtils;
import com.xiejh.common.utils.Query;

import com.xiejh.product.dao.CategoryDao;
import com.xiejh.product.entity.CategoryEntity;
import com.xiejh.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listTree() {
        List<CategoryEntity> list = baseMapper.selectList(null);
        List<CategoryEntity> roots = list.stream().filter(e -> e.getCatLevel() == 1)
                .sorted((a, b) -> (a.getSort() != null ? a.getSort() : 0) - (b.getSort() != null ? b.getSort() : 0))
                .collect(Collectors.toList());
        roots.forEach(e -> e.setChildren(getChildren(e, list)));
        return roots;
    }

    /**
     * 设置子孙节点
     *
     * @param parent
     * @param all
     * @return
     */
    public List<CategoryEntity> getChildren(CategoryEntity parent, List<CategoryEntity> all) {
        return all.stream().filter(e -> e.getParentCid() == parent.getCatId())
                .map(e -> {
                    e.setChildren(getChildren(e, all));
                    return e;
                }).sorted((a, b) -> (a.getSort() != null ? a.getSort() : 0) - (b.getSort() != null ? b.getSort() : 0))
                .collect(Collectors.toList());
    }

}