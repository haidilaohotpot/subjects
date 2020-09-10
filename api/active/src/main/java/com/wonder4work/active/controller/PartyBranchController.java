package com.wonder4work.active.controller;


import com.wonder4work.active.domain.Activity;
import com.wonder4work.active.domain.PartyBranch;
import com.wonder4work.active.service.PartyBranchService;
import com.wonder4work.active.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private PartyBranchService partyBranchService;

    @GetMapping("/list")
    @ApiOperation(value = "xiezengcheng:查询所有的党支部",notes = "查询所有的党支部",response = PartyBranch.class)
    public JSONResult list() {

        return JSONResult.ok(partyBranchService.list());

    }

}

