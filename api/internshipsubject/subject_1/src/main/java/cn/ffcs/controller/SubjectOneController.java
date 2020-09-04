package cn.ffcs.controller;

import cn.ffcs.bean.User;
import cn.ffcs.bo.UserBO;
import cn.ffcs.service.UserService;
import cn.ffcs.util.Dom4jUtils;
import cn.ffcs.util.PagedGridResult;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.tree.DefaultDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xiezengcheng
 * @date 2020-08-06
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class SubjectOneController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 分页查询用户信息数据
     * @param page
     * @param pageSize
     * @param queryText
     * @return
     */
    @GetMapping("/pageQuery")
    public JsonResult pageQuery(@RequestParam(required = false,defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "10") int pageSize, String queryText) {
        PagedGridResult pagedGridResult = userService.pageQuery(page, pageSize, queryText);
        return JsonResult.success(pagedGridResult);
    }


    @PostMapping("/update")
    public JsonResult update(@Valid UserBO userBO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorsString = getErrorsString(bindingResult);
            return JsonResult.failed(errorsString);
        }

        if (null == userBO || null == userBO.getId()) {
            return JsonResult.failed("用户ID不能为空");
        }

        boolean checkAge = checkAge(userBO.getAge());

        if (!checkAge) {
            return JsonResult.failed("年龄必须在1~120之间");
        }
        userService.update(userBO);
        return JsonResult.success();
    }

    @PostMapping("/create")
    public JsonResult create(@Valid UserBO userBO, BindingResult bindingResult) {

        // 校验数据是否有错误
        if (bindingResult.hasErrors()) {
            String errorsString = getErrorsString(bindingResult);
            return JsonResult.failed(errorsString);
        }

        String code = userBO.getCode();
        if (StringUtils.isBlank(code)) {
            return JsonResult.failed("员工代码不能为空");
        }

        boolean checkCode = userService.checkCode(code);

        if (checkCode) {
            return JsonResult.failed("员工代码已经存在");
        }

        boolean checkAge = checkAge(userBO.getAge());

        if (!checkAge) {
            return JsonResult.failed("年龄必须在1~120之间");
        }

        userService.create(userBO);
        return JsonResult.success();
    }


    @PostMapping("/delete/{id}")
    public JsonResult delete(@PathVariable(value = "id",required = true) Integer id) {

        if (null == id) {
            return JsonResult.failed("用户ID不能为空");
        }
        userService.delete(id);
        return JsonResult.success();
    }

    /**
     * 校验年龄 1~120岁之间
     * @param age
     * @return
     */
    private boolean checkAge(Integer age) {
        if (null == age){
            return false;
        }
        return age > 0 && age <= 120;
    }

}
