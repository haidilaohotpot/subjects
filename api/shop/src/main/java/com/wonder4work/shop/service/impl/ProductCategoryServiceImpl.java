package com.wonder4work.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wonder4work.shop.domain.ProductCategory;
import com.wonder4work.shop.mapper.ProductCategoryMapper;
import com.wonder4work.shop.service.ProductCategoryService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author xiezengcheng
 * @date 2020-09-14
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper,ProductCategory> implements ProductCategoryService {

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return this.getById(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return this.list();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {

        QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
        if (categoryTypeList != null && categoryTypeList.size() > 0) {
            queryWrapper.in("category_type", categoryTypeList);
        }
        List<ProductCategory> productCategoryList = this.list(queryWrapper);
        return productCategoryList;
    }

    @Override
    public void saveOne(ProductCategory productCategory) {
        this.save(productCategory);
    }
}
