package com.wonder4work.shop.controller.buyer;

import com.wonder4work.shop.domain.ProductCategory;
import com.wonder4work.shop.domain.ProductInfo;
import com.wonder4work.shop.service.ProductCategoryService;
import com.wonder4work.shop.service.ProductService;
import com.wonder4work.shop.utils.JSONResult;
import com.wonder4work.shop.utils.ResultVOUtil;
import com.wonder4work.shop.vo.ProductInfoVO;
import com.wonder4work.shop.vo.ProductVO;
import com.wonder4work.shop.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiezengcheng
 * @date 2020-09-16
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list() {
        //1. 查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2. 查询类目(一次性查询)
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3. 数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory: productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo: productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }



}
