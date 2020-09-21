package com.wonder4work.shop.controller;

import com.wonder4work.shop.config.ProjectUrlConfig;
import com.wonder4work.shop.domain.SellerInfo;
import com.wonder4work.shop.enums.ResultEnum;
import com.wonder4work.shop.service.SellerService;
import com.wonder4work.shop.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;


    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public JSONResult login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String, Object> map) {

        //1. openid去和数据库里的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (sellerInfo == null) {
            return JSONResult.errorMsg("用户不存在");
        }

        //2. 设置token至redis

        //3. 设置token至cookie
        return JSONResult.ok();

    }

    @GetMapping("/logout")
    public JSONResult logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Map<String, Object> map) {
        //1. 从cookie里查询

        return JSONResult.ok();
    }
}
