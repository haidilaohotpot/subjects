package com.wonder4work.active.controller;


import com.wonder4work.active.domain.Activity;
import com.wonder4work.active.domain.PartyBranch;
import com.wonder4work.active.service.PartyBranchService;
import com.wonder4work.active.utils.JSONResult;
import com.wonder4work.active.utils.JsonUtils;
import com.wonder4work.active.utils.RedisOperator;
import com.wonder4work.active.vo.CommentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.minidev.json.JSONUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
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
@Api(tags = {"党支部相关接口"})
@RestController
@RequestMapping("/partyBranch")
public class PartyBranchController {

    @Autowired
    private RedisOperator redisOperator;

    @Autowired
    private PartyBranchService partyBranchService;


    @ApiImplicitParams({
            @ApiImplicitParam(name = "partyBranchName",value = "党支部",dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "page",value = "页码",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "pageSize",value = "每页显示多少条",dataType = "int",paramType = "query")
    })
    @GetMapping("/query")
    @ApiOperation(value = "xiezengcheng:分页条件查询党支部信息",notes = "分页条件查询党支部信息",response = Activity.class)
    public JSONResult query(@RequestParam(required = false) Map<String, Object> queryMap,
                            @RequestParam(required = false,defaultValue = "1") Integer page,
                            @RequestParam(required = false,defaultValue = "20") Integer pageSize) {

        return JSONResult.ok(partyBranchService.query(queryMap, page, pageSize));
    }

    @GetMapping("/list")
    @ApiOperation(value = "xiezengcheng:查询所有的党支部",notes = "查询所有的党支部",response = PartyBranch.class)
    public JSONResult list() {
        List<PartyBranch> partyBranchList = new ArrayList<>();
        String branchList = redisOperator.get("partyBranchList");
        if (StringUtils.isBlank(branchList)) {
            partyBranchList = partyBranchService.list();
            redisOperator.set("partyBranchList", JsonUtils.objectToJson(partyBranchList));
        }else{
            partyBranchList = JsonUtils.jsonToList(branchList, PartyBranch.class);
        }

        return JSONResult.ok(partyBranchList);

    }

    @ApiOperation(value = "xiezengcheng:根据ID查询党支部信息",notes = "根据ID查询党支部信息",response = PartyBranch.class)
    @GetMapping("/findById/{id}")
    public JSONResult findById(@PathVariable(value = "id")Integer id){

        PartyBranch partyBranch = partyBranchService.getById(id);

        return JSONResult.ok(partyBranch);
    }


    @ApiOperation(value = "新增/编辑党支部", notes = "新增/编辑党支部", httpMethod = "POST",response = PartyBranch.class)
    @PostMapping("/createOrUpdate")
    public JSONResult createOrUpdate(@RequestBody PartyBranch partyBranch) throws Exception {

        if (StringUtils.isBlank(partyBranch.getPartyBranchName())) {
            return JSONResult.errorMsg("支部名称不能为空");
        }

        // 当党支部信息有更改时删除redis中原有的缓存
        redisOperator.del("partyBranchList");

        PartyBranch byPartyBranchName = partyBranchService.findByPartyBranchName(partyBranch.getPartyBranchName());
        if (partyBranch.getId() != null) {

            if (byPartyBranchName != null && !byPartyBranchName.getId().equals(partyBranch.getId())) {

                return JSONResult.errorMsg("党支部已经存在");
            }

            partyBranchService.updateById(partyBranch);

            return JSONResult.ok();
        }

        if (byPartyBranchName != null && byPartyBranchName.getId() != null) {
            return JSONResult.errorMsg("党支部已经存在");
        }

        partyBranchService.save(partyBranch);
        return JSONResult.ok();
    }

}

