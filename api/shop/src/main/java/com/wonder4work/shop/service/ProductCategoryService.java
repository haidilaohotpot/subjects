package com.wonder4work.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wonder4work.shop.domain.ProductCategory;

import java.util.List;

/**
 * @author xiezengcheng
 * @date 2020-09-14
 */
public interface ProductCategoryService extends IService<ProductCategory> {

    /**
     * 根据类别ID查询商品类别
     * @param categoryId
     * @return
     */
    ProductCategory findOne(Integer categoryId);

    /**
     * 查询所有的商品类别
     * @return
     */
    List<ProductCategory> findAll();

    /**
     * 根据类目编号查询商品类别
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 保存
     * @param productCategory
     */
    void saveOne(ProductCategory productCategory);

}
