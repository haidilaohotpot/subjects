package com.wonder4work.active.controller;


import com.wonder4work.active.bo.UserBO;
import com.wonder4work.active.service.UserService;
import com.wonder4work.active.utils.*;
import com.wonder4work.active.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wonder4work
 * @since 2020-09-07
 */
@Slf4j
@Api(tags = {"用户相关接口"})
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;



    @ApiOperation(value = "注册", notes = "注册", httpMethod = "POST",response = UserVO.class)
    @PostMapping("/registe")
    public JSONResult registe(@ApiParam(name = "userBO",value = "用户信息",required = true) @RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.info(userBO.toString());

        UserVO userVO = userService.registe(userBO);

        return JSONResult.ok(userVO);
    }

    @ApiOperation(value = "登录", notes = "登录", httpMethod = "POST",response = UserVO.class)
    @PostMapping("/login")
    public JSONResult login(@ApiParam(name = "userBO",value = "登录用户信息",required = true) @RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String password = userBO.getPassword();
        String username = userBO.getUsername();
        String code = userBO.getVercode();
        if (StringUtils.isBlank(code)) {
            return JSONResult.errorMsg("验证码不能为空");
        }
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return JSONResult.errorMsg("用户名或密码不能为空");
        }

        boolean checkVerifyCode = CodeUtil.checkVerifyCode(request, code);
        if (!checkVerifyCode) {
            return JSONResult.errorMsg("验证码错误");
        }

        if (!MobileEmailUtils.checkMobileIsOk(username)){
            return JSONResult.errorMsg("用户名密码不正确");
        }

        // 根据用户名(登录名或手机号)和密码查询用户
        UserVO loginUser = userService.login(username, password);

        if (loginUser.getId() == null) {
            return JSONResult.errorMsg("用户名或密码错误");

        }

        // 设置cookie
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(loginUser), true);

        return JSONResult.ok(loginUser);
    }

    @ApiOperation(value = "退出登录",notes = "用户退出登录的接口",httpMethod = "POST")
    @PostMapping("/logout")
    public JSONResult logout(HttpServletRequest request, HttpServletResponse response){

        // 清除用户信息
        CookieUtils.deleteCookie(request, response, "user");

        return JSONResult.ok();
    }

}

