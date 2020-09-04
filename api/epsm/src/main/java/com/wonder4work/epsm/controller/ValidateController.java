package com.wonder4work.epsm.controller;

import com.wonder4work.epsm.domain.User;
import com.wonder4work.epsm.domain.UserBO;
import com.wonder4work.epsm.domain.ValidateVO;
import com.wonder4work.epsm.service.ValidateService;
import com.wonder4work.epsm.utils.CookieUtils;
import com.wonder4work.epsm.utils.JSONResult;
import com.wonder4work.epsm.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiezengcheng
 * @date 2020-09-03
 */
@Api(value = "登录登出相关接口", tags = "登录登出相关接口")
@RestController
@RequestMapping("/validate")
public class ValidateController {


    @Autowired
    private ValidateService validateService;

    @ApiOperation(value = "登录", notes = "登录", httpMethod = "POST",response = UserBO.class)
    @PostMapping("/login")
    public JSONResult login(@ApiParam(name = "validateVO",value = "登录信息",required = false) @RequestBody ValidateVO validateVO, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String password = validateVO.getPassword();
        String username = validateVO.getUsername();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return JSONResult.errorMsg("用户名或密码不能为空");
        }

        // 根据用户名(登录名或手机号)和密码查询用户
        User loginUser = validateService.login(username, password);
        loginUser.setPassword("");
        if (loginUser.getUserId() == null) {
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
