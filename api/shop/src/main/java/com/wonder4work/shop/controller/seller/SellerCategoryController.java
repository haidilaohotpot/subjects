package com.wonder4work.shop.controller.seller;

import com.wonder4work.shop.domain.ProductCategory;
import com.wonder4work.shop.enums.ResultEnum;
import com.wonder4work.shop.exception.SellException;
import com.wonder4work.shop.form.CategoryForm;
import com.wonder4work.shop.service.ProductCategoryService;
import com.wonder4work.shop.utils.PagedGridResult;
import com.wonder4work.shop.utils.ResultVOUtil;
import com.wonder4work.shop.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 商品类目
 * @author xiezengcheng
 */
@RestController
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService categoryService;

    /**
     * 类目列表
     * @return
     */
    @GetMapping("/list")
    public ResultVO list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                         @RequestParam(value = "pageSize", defaultValue = "100") Integer pageSize) {
        PagedGridResult pagedGridResult = categoryService.findAll(page, pageSize);
        return ResultVOUtil.success(pagedGridResult);
    }

    /**
     * 展示
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ResultVO index(@RequestParam(value = "categoryId", required = false) Integer categoryId) {
        if (categoryId == null) {
            return ResultVOUtil.success();
        }
        ProductCategory productCategory = categoryService.findOne(categoryId);
        return ResultVOUtil.success(productCategory);
    }

    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ResultVO save(@Valid @RequestBody CategoryForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        ProductCategory productCategory = new ProductCategory();
        try {
            if (form.getCategoryId() != null) {
                productCategory = categoryService.findOne(form.getCategoryId());
                BeanUtils.copyProperties(form, productCategory);
                categoryService.updateById(productCategory);
            }else{
                BeanUtils.copyProperties(form, productCategory);
                productCategory.setCategoryType(0);
                categoryService.save(productCategory);
            }

        } catch (SellException e) {

            return ResultVOUtil.error(e.getCode(), e.getMessage());
        }


        return ResultVOUtil.success();
    }
}
