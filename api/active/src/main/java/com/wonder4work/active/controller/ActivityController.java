package com.wonder4work.active.controller;


import com.wonder4work.active.bo.UserBO;
import com.wonder4work.active.domain.Activity;
import com.wonder4work.active.domain.PartyBranch;
import com.wonder4work.active.service.ActivityService;
import com.wonder4work.active.utils.JSONResult;
import com.wonder4work.active.vo.CommentVO;
import com.wonder4work.active.vo.UserVO;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wonder4work
 * @since 2020-09-07
 */
@Slf4j
@Api(tags = {"活动相关接口"})
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;


    @ApiImplicitParams({
            @ApiImplicitParam(name = "partyBranch",value = "所属党支部",dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "activityTheme",value = "活动主题",dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "activityStatus",value = "活动状态 -1 已取消 0 报名已结束 1 报名中 2 进行中 3 活动已结束 4 未开始",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "userId",value = "用户ID",dataType = "int",paramType = "query"),
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

    @ApiImplicitParams({
            @ApiImplicitParam(name = "partyBranch",value = "所属党支部",dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "name",value = "姓名",dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "page",value = "页码",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "pageSize",value = "每页显示多少条",dataType = "int",paramType = "query")
    })

    @GetMapping("/signUpDetail")
    @ApiOperation(value = "xiezengcheng:分页查询活动报名人员信息",notes = "分页查询活动报名人员信息",response = Activity.class)
    public JSONResult signUpDetail(@RequestParam(required = false) Map<String, Object> queryMap,
                            @RequestParam(required = false,defaultValue = "1") Integer page,
                            @RequestParam(required = false,defaultValue = "20") Integer pageSize) {

        String activityId = (String) queryMap.get("activityId");

        if (StringUtils.isBlank(activityId)){
            return JSONResult.errorMsg("活动ID不能为空");
        }

        return JSONResult.ok(activityService.signUpDetail(queryMap, page, pageSize));
    }


    @ApiOperation(value = "添加/编辑活动", notes = "添加/编辑活动", httpMethod = "POST",response = Activity.class)
    @PostMapping("/createOrUpdate")
    public JSONResult createOrUpdate(@ApiParam(name = "activity",value = "活动信息",required = true) @RequestBody Activity activity, HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.info(activity.toString());

        if (activity.getId()==null) {
            activityService.create(activity);
        }else{
            activityService.update(activity);
        }

        return JSONResult.ok();
    }

    @ApiOperation(value = "取消活动", notes = "取消活动", httpMethod = "POST",response = Activity.class)
    @PostMapping("/cancel/{activityId}")
    public JSONResult cancel(@PathVariable(name = "activityId",required = true)Integer activityId) throws Exception {

        if (activityId == null) {
            return JSONResult.errorMsg("活动ID不能为空");
        }


        activityService.cancel(activityId);

        return JSONResult.ok();
    }


    @ApiOperation(value = "xiezengcheng:根据ID查询活动信息",notes = "根据ID查询活动信息",response = Activity.class)
    @GetMapping("/findById/{id}")
    public JSONResult findById(@PathVariable(value = "id")Integer id){

        Activity activity = activityService.getById(id);

        return JSONResult.ok(activity);
    }


    @ApiOperation(value = "活动报名/取消", notes = "活动报名/取消", httpMethod = "POST",response = Activity.class)
    @PostMapping("/joinOrCancel")
    public JSONResult joinOrCancel(@RequestParam(name = "tag", required = true)Integer tag,@RequestParam(name = "userId", required = true)Integer userId,@RequestParam(name = "activityId",required = true)Integer activityId) throws Exception {

        // tag ==0 取消报名  tag==1报名

        if (tag ==null || activityId == null||userId==null) {
            return JSONResult.errorMsg("活动ID和用户ID不能为空");
        }

        if (tag == 1) {
            boolean isJoin = activityService.checkUserIsJoin(userId, activityId);
            if (isJoin) {
                return JSONResult.ok();
            }
            activityService.join(userId,activityId);
        }

        if (tag == 0) {
            activityService.cancel(userId,activityId);
        }

        return JSONResult.ok();
    }


    @ApiOperation(value = "xiezengcheng:查询用户是否报名此活动",notes = "查询用户是否报名此活动",response = boolean.class)
    @GetMapping("/checkUserIsJoin")
    public JSONResult checkUserIsJoin(@RequestParam(name = "userId", required = true)Integer userId,@RequestParam(name = "activityId",required = true)Integer activityId){
        if (activityId == null||userId==null) {
            return JSONResult.errorMsg("活动ID和用户ID不能为空");
        }
        boolean isJoin = activityService.checkUserIsJoin(userId, activityId);

        return JSONResult.ok(isJoin);
    }

    @ApiOperation(value = "点赞",notes = "点赞",response = boolean.class)
    @GetMapping("/like")
    public JSONResult like(@RequestParam(name = "activityId",required = true)Integer activityId){
        if (activityId == null) {
            return JSONResult.errorMsg("活动ID不能为空");
        }
        activityService.like(activityId);

        return JSONResult.ok();
    }


    @ApiImplicitParams({

            @ApiImplicitParam(name = "userId",value = "用户ID",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "activityId",value = "活动ID",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "name",value = "姓名",dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "page",value = "页码",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "pageSize",value = "每页显示多少条",dataType = "int",paramType = "query")
    })

    @GetMapping("/queryComments")
    @ApiOperation(value = "xiezengcheng:分页查询活动评论信息",notes = "分页查询活动评论信息",response = CommentVO.class)
    public JSONResult queryComments(@RequestParam(required = false) Map<String, Object> queryMap,
                                   @RequestParam(required = false,defaultValue = "1") Integer page,
                                   @RequestParam(required = false,defaultValue = "20") Integer pageSize) {

        String activityId = (String) queryMap.get("activityId");

        if (StringUtils.isBlank(activityId)){
            return JSONResult.errorMsg("活动ID不能为空");
        }

        return JSONResult.ok(activityService.queryComment(queryMap, page, pageSize));
    }

    @ApiOperation(value = "删除评论", notes = "删除评论", httpMethod = "POST",response = CommentVO.class)
    @PostMapping("/deleteComment")
    public JSONResult deleteComment(@RequestParam(name = "id",required = true)Integer id,@RequestParam(name = "activityId",required = true)Integer activityId) throws Exception {

        if (activityId == null || id == null) {
            return JSONResult.errorMsg("ID不能为空");
        }

        activityService.deleteComment(id,activityId);

        return JSONResult.ok();
    }

    @ApiOperation(value = "评论", notes = "评论", httpMethod = "POST",response = CommentVO.class)
    @PostMapping("/comment")
    public JSONResult comment(@RequestBody CommentVO commentVO) throws Exception {

        if (commentVO.getActivityId() == null || commentVO.getUserId() == null) {
            return JSONResult.errorMsg("ID不能为空");
        }

        if (StringUtils.isBlank(commentVO.getMsg())) {
            return JSONResult.errorMsg("评论不能为空");
        }


        return JSONResult.ok(activityService.comment(commentVO));
    }

}

