package com.wonder4work.active.controller;


import com.wonder4work.active.domain.Log;
import com.wonder4work.active.service.LogService;
import com.wonder4work.active.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wonder4work
 * @since 2020-09-07
 */
@Api(tags = {"日志相关接口"})
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/query")
    @ApiOperation(value = "分页条件查询日志信息",notes = "根据条件查询日志信息，支持分页")
    public JSONResult query() {

        List<Log> list = logService.list();
        return JSONResult.ok(list);
    }

}

