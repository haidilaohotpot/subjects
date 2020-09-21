package com.wonder4work.shop.controller.seller;

import com.wonder4work.shop.domain.ProductCategory;
import com.wonder4work.shop.domain.ProductInfo;
import com.wonder4work.shop.enums.ResultEnum;
import com.wonder4work.shop.exception.SellException;
import com.wonder4work.shop.form.ProductForm;
import com.wonder4work.shop.service.ProductCategoryService;
import com.wonder4work.shop.service.ProductService;
import com.wonder4work.shop.utils.KeyUtil;
import com.wonder4work.shop.utils.PagedGridResult;
import com.wonder4work.shop.utils.ResultVOUtil;
import com.wonder4work.shop.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家端商品
 */
@RestController
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService categoryService;

    /**
     * 列表
     * @param page
     * @param pageSize
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ResultVO list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                         @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
                         @RequestParam Map<String, Object> map) {
        PagedGridResult pagedGridResult = productService.findAll(map,page, pageSize);
        return ResultVOUtil.success(pagedGridResult);
    }

    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/on_sale")
    public ResultVO onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        try {
            productService.onSale(productId);
        } catch (SellException e) {
            return ResultVOUtil.error(e.getCode(), e.getMessage());
        }

        return ResultVOUtil.success();
    }
    /**
     * 商品下架
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/off_sale")
    public ResultVO offSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            return ResultVOUtil.error(e.getCode(),e.getMessage());
        }
        return ResultVOUtil.success();
    }

    @GetMapping("/index")
    public ResultVO index(@RequestParam(value = "productId", required = false) String productId,
                      Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo);
        }

        //查询所有的类目
        List<ProductCategory> categoryList = categoryService.list();
        map.put("categoryList", categoryList);

        return ResultVOUtil.success(map);
    }

    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    @CacheEvict(cacheNames = "product", allEntries = true, beforeInvocation = true)
    public ResultVO save(@RequestBody @Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        ProductInfo productInfo = new ProductInfo();
        try {
            //如果productId为空, 说明是新增
            if (!StringUtils.isEmpty(form.getProductId())) {
                BeanUtils.copyProperties(form, productInfo);
                productService.updateById(productInfo);
            } else {
                form.setProductId(KeyUtil.genUniqueKey());
                BeanUtils.copyProperties(form, productInfo);
                productService.save(productInfo);
            }

        } catch (SellException e) {
            return ResultVOUtil.error(e.getCode(), e.getMessage());
        }
        return ResultVOUtil.success();
    }
}
