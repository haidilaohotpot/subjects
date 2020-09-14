package com.wonder4work.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wonder4work.shop.domain.ProductCategory;
import com.wonder4work.shop.mapper.ProductCategoryMapper;
import com.wonder4work.shop.service.ProductCategoryService;
import org.springframework.stereotype.Service;

/**
 * @author xiezengcheng
 * @date 2020-09-14
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper,ProductCategory> implements ProductCategoryService {
}
