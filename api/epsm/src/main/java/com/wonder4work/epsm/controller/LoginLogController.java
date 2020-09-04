package com.wonder4work.epsm.controller;


import com.wonder4work.epsm.domain.LoginLog;
import com.wonder4work.epsm.domain.UserBO;
import com.wonder4work.epsm.service.LoginLogService;
import com.wonder4work.epsm.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 登录日志表 前端控制器
 * </p>
 *
 * @author wonder4work
 * @since 2020-08-12
 */
@Api(value = "登录日志相关接口", tags = "登录日志相关接口")
@RestController
@RequestMapping("/loginLog")
public class LoginLogController {

    @Autowired
    private LoginLogService loginLogService;

    @ApiOperation(value = "分页查询登录日志列表", notes = "分页查询登录日志列表", httpMethod = "GET",response = LoginLog.class)
    @GetMapping("/query")
    public JSONResult query(@ApiParam(name = "queryMap",value = "条件查询参数",required = false) @RequestParam Map<String, Object> queryMap,
                            @ApiParam(name = "page",value = "页码",required = false) @RequestParam(defaultValue = "1") Integer page,
                            @ApiParam(name = "pageSize",value = "每页显示多少条",required = false) @RequestParam(defaultValue = "20") Integer pageSize) {
        return JSONResult.ok(loginLogService.query(queryMap, page, pageSize));
    }

}

