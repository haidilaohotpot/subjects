package com.wonder4work.epsm.controller;

import com.wonder4work.epsm.domain.Exception;
import com.wonder4work.epsm.domain.InterfaceLog;
import com.wonder4work.epsm.service.ExceptionService;
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
 * @author xiezengcheng
 * @date 2020-09-02
 */
@Api(value = "异常日志相关接口", tags = "异常日志相关接口")
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @Autowired
    private ExceptionService exceptionService;

    @ApiOperation(value = "分页查询异常信息列表", notes = "分页查询异常信息列表", httpMethod = "GET",response = Exception.class)
    @GetMapping("/query")
    public JSONResult query(@ApiParam(name = "queryMap",value = "条件查询参数",required = false) @RequestParam Map<String, Object> queryMap,
                            @ApiParam(name = "page",value = "页码",required = false) @RequestParam(defaultValue = "1") Integer page,
                            @ApiParam(name = "pageSize",value = "每页显示多少条",required = false) @RequestParam(defaultValue = "20") Integer pageSize) {
        return JSONResult.ok(exceptionService.query(queryMap, page, pageSize));
    }


}
