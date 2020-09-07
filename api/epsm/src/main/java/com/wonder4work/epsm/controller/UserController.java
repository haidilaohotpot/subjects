package com.wonder4work.epsm.controller;


import com.wonder4work.epsm.domain.User;
import com.wonder4work.epsm.domain.UserBO;
import com.wonder4work.epsm.service.UserService;
import com.wonder4work.epsm.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author wonder4work
 * @since 2020-08-12
 */
@Api(value = "用户信息接口", tags = "用户信息相关接口")
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    @ApiOperation(value = "删除用户信息", notes = "删除用户信息", httpMethod = "POST")
    @PostMapping("/delete/{userId}")
    public JSONResult delete(@PathVariable(value = "userId",required = true) Integer userId) {

        userService.delete(userId);
        return JSONResult.ok();
    }

    @ApiOperation(value = "批量删除用户信息", notes = "批量删除用户信息", httpMethod = "POST")
    @PostMapping("/delete")
    public JSONResult delete(@RequestBody List<Integer> userIdList) {

        if (null != userIdList && userIdList.size() > 0) {
            userService.deletes(userIdList);
        }
        return JSONResult.ok();
    }

    @ApiOperation(value = "更新或创建用户", notes = "更新或创建用户", httpMethod = "POST")
    @PostMapping("/createOrUpdate")
    public JSONResult createOrUpdate(@RequestBody @Validated UserBO userBO, BindingResult bindingResult) throws Exception {

        // 判断验证中是否有错误
        if (bindingResult.hasErrors()) {
            Map<String, Object> objectMap = getErrors(bindingResult);
            return JSONResult.errorMap(objectMap);
        }

        boolean isExist = checkLoginNameOrPhoneIsExist(userBO);
        if (isExist){
            return JSONResult.errorMsg("登录名或手机号已经存在");
        }

        // 更新
        if (null != userBO.getUserId()) {
            userService.update(userBO);
            return JSONResult.ok();
        }

        userService.create(userBO);
        // 添加
        return JSONResult.ok();
    }

    @ApiOperation(value = "分页查询用户信息列表", notes = "分页查询用户信息列表", httpMethod = "GET",response = UserBO.class)
    @GetMapping("/query")
    public JSONResult query(@ApiParam(name = "queryMap",value = "条件查询参数",required = false) @RequestParam Map<String, Object> queryMap,
                            @ApiParam(name = "page",value = "页码",required = false) @RequestParam(defaultValue = "1") Integer page,
                            @ApiParam(name = "pageSize",value = "每页显示多少条",required = false) @RequestParam(defaultValue = "20") Integer pageSize){
        return JSONResult.ok(userService.query(queryMap, page, pageSize));
    }


    @ApiOperation(value = "根据用户ID查询用户信息", notes = "根据用户ID查询用户信息", httpMethod = "GET")
    @GetMapping("/query/{userId}")
    public JSONResult findByUserId(@PathVariable(value = "userId",required = true) Integer userId) {
        return JSONResult.ok(userService.findByUserId(userId));
    }

    /**
     * 获取树胶校验的错误信息
     * @param result
     * @return
     */
    private Map<String, Object> getErrors(BindingResult result) {

        List<FieldError> errorList = result.getFieldErrors();
        Map<String, Object> map = new HashMap<>();
        for (FieldError fieldError : errorList) {
            // 某一个属性
            String field = fieldError.getField();
            // 验证错误的信息
            String defaultMessage = fieldError.getDefaultMessage();

            map.put(field, defaultMessage);
        }
        return map;
    }

    /**
     * 检查登录名和手机号是否已经存在
     * @param userBO
     * @return
     */
    private boolean checkLoginNameOrPhoneIsExist(UserBO userBO) {

        List<User> userList = userService.findByLoinNameOrPhone(userBO.getLoginName(), userBO.getPhone());
        if (null == userList || userList.size() == 0) {
            return false;
        }

        if (null != userBO.getUserId()) {
            for (User user : userList) {
                if (!user.getUserId().equals(userBO.getUserId())) {
                    return true;
                }
            }
            return false;
        }

        return true;
    }
}

