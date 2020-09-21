package com.wonder4work.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.wonder4work.shop.domain.ProductInfo;
import com.wonder4work.shop.dto.CartDTO;
import com.wonder4work.shop.enums.ProductStatusEnum;
import com.wonder4work.shop.enums.ResultEnum;
import com.wonder4work.shop.exception.SellException;
import com.wonder4work.shop.mapper.ProductInfoMapper;
import com.wonder4work.shop.service.ProductService;
import com.wonder4work.shop.utils.PageUtil;
import com.wonder4work.shop.utils.PagedGridResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author xiezengcheng
 * @date 2020-09-16
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductInfoMapper,ProductInfo> implements ProductService {
    @Override
    public ProductInfo findOne(String productId) {
        return this.getById(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {

        QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("product_status",ProductStatusEnum.UP.getCode());

        List<ProductInfo> productInfoList = this.list(queryWrapper);

        return productInfoList;
    }

    @Override
    public PagedGridResult findAll(Map<String, Object> map,Integer page, Integer pageSize) {

        PageHelper.startPage(page, pageSize);

        String productName = (String)map.get("productName");
        String categoryType = (String)map.get("categoryType");

        QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(productName)) {
            queryWrapper.like("product_name", productName);
        }

        if (StringUtils.isNotBlank(categoryType)) {
            queryWrapper.eq("category_type", categoryType);
        }
        List<ProductInfo> list = this.list(queryWrapper);

        PagedGridResult pagedGridResult = PageUtil.setterPagedGrid(page, list);

        return pagedGridResult;


    }

    @Override
    public void saveOne(ProductInfo productInfo) {
         this.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            ProductInfo productInfo = this.getById(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);

            this.updateById(productInfo);
        }

    }

    @Override
    @Transactional
    public  void  decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            ProductInfo productInfo = this.getById(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }


            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);

            this.updateById(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = this.getById(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        this.updateById(productInfo);
        return productInfo;
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = this.getById(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        this.updateById(productInfo);
        return productInfo;
    }
}
