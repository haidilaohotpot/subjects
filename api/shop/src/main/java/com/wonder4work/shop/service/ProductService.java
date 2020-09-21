package com.wonder4work.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wonder4work.shop.domain.ProductInfo;
import com.wonder4work.shop.dto.CartDTO;
import com.wonder4work.shop.utils.PagedGridResult;

import java.util.List;
import java.util.Map;

/**
 * @author xiezengcheng
 * @date 2020-09-16
 */
public interface ProductService extends IService<ProductInfo> {

    ProductInfo findOne(String productId);

    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    PagedGridResult findAll(Map<String, Object> map, Integer page, Integer pageSize);

    void saveOne(ProductInfo productInfo);

//    加库存
    void increaseStock(List<CartDTO> cartDTOList);

//    减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
    ProductInfo onSale(String productId);

    //下架
    ProductInfo offSale(String productId);

}
