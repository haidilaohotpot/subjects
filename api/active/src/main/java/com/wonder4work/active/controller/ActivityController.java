package com.wonder4work.active.controller;


import com.wonder4work.active.domain.Activity;
import com.wonder4work.active.service.ActivityService;
import com.wonder4work.active.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wonder4work
 * @since 2020-09-07
 */
@Api(tags = {"活动相关接口"})
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "partyBranch",value = "所属党支部",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "activityTheme",value = "活动主题",dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "activityStatus",value = "活动状态 -1 已取消 0 报名已结束 1 报名中 2 进行中 3 活动已结束 4 未开始",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "page",value = "页码",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "pageSize",value = "每页显示多少条",dataType = "int",paramType = "query")
    })
    @GetMapping("/query")
    @ApiOperation(value = "xiezengcheng:分页条件查询活动信息",notes = "分页条件查询活动信息",response = Activity.class)
    public JSONResult query(@RequestParam(required = false) Map<String, Object> queryMap,
                            @RequestParam(required = false,defaultValue = "1") Integer page,
                            @RequestParam(required = false,defaultValue = "20") Integer pageSize) {
        return JSONResult.ok(activityService.query(queryMap, page, pageSize));
    }

}

