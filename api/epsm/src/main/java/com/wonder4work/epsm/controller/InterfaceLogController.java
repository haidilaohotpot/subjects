package com.wonder4work.epsm.controller;


import com.wonder4work.epsm.domain.InterfaceLog;
import com.wonder4work.epsm.domain.UserBO;
import com.wonder4work.epsm.service.ExceptionService;
import com.wonder4work.epsm.service.InterfaceLogService;
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
 * 接口调用日志表
 前端控制器
 * </p>
 *
 * @author wonder4work
 * @since 2020-08-12
 */
@Api(value = "调用日志相关接口", tags = "调用日志相关接口")
@RestController
@RequestMapping("/interfaceLog")
public class InterfaceLogController {
    @Autowired
    private InterfaceLogService interfaceLogService;

    @ApiOperation(value = "分页查询调用日志列表", notes = "分页查询调用日志列表", httpMethod = "GET",response = InterfaceLog.class)
    @GetMapping("/query")
    public JSONResult query(@ApiParam(name = "queryMap",value = "条件查询参数",required = false) @RequestParam Map<String, Object> queryMap,
                            @ApiParam(name = "page",value = "页码",required = false) @RequestParam(defaultValue = "1") Integer page,
                            @ApiParam(name = "pageSize",value = "每页显示多少条",required = false) @RequestParam(defaultValue = "20") Integer pageSize) {
        return JSONResult.ok(interfaceLogService.query(queryMap, page, pageSize));
    }
}

