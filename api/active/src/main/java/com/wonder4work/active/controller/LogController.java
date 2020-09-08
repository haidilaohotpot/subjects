package com.wonder4work.active.controller;


import com.wonder4work.active.domain.Log;
import com.wonder4work.active.service.LogService;
import com.wonder4work.active.utils.JSONResult;
import com.wonder4work.active.utils.PagedGridResult;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    @ApiImplicitParams({
            @ApiImplicitParam(name = "content",value = "日志内容",dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "api",value = "接口",dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "createTimeMax",value = "时间下区间",dataType = "string",example = "2020-09-07",paramType = "query"),
            @ApiImplicitParam(name = "createTimeMin",value = "时间上区间",dataType = "string",example = "2020-09-08",paramType = "query"),
            @ApiImplicitParam(name = "page",value = "页码",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "pageSize",value = "每页显示多少条",dataType = "int",paramType = "query")
    })
    @GetMapping("/query")
    @ApiOperation(value = "xiezengcheng:分页条件查询日志信息",notes = "根据条件查询日志信息，支持分页",response = Log.class)
    public JSONResult query(@RequestParam(required = false) Map<String, Object> queryMap,
                            @RequestParam(required = false,defaultValue = "1") Integer page,
                            @RequestParam(required = false,defaultValue = "20") Integer pageSize) {
        return JSONResult.ok(logService.query(queryMap, page, pageSize));
    }

}

