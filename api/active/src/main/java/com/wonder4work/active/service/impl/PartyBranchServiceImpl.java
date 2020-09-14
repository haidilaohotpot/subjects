package com.wonder4work.active.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.wonder4work.active.domain.PartyBranch;
import com.wonder4work.active.mapper.PartyBranchMapper;
import com.wonder4work.active.service.PartyBranchService;
import com.wonder4work.active.utils.PageUtil;
import com.wonder4work.active.utils.PagedGridResult;
import com.wonder4work.active.vo.ActivityUserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wonder4work
 * @since 2020-09-07
 */
@Service
public class PartyBranchServiceImpl extends ServiceImpl<PartyBranchMapper, PartyBranch> implements PartyBranchService {


    @Override
    public PagedGridResult query(Map<String, Object> queryMap, Integer page, Integer pageSize) {

        String partyBranchName = (String) queryMap.get("partyBranchName");


        QueryWrapper<PartyBranch> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotBlank(partyBranchName)) {
            queryWrapper.like("party_branch_name", partyBranchName);
        }

        PageHelper.startPage(page, pageSize);

        List<PartyBranch> partyBranchList = this.baseMapper.selectList(queryWrapper);


        return PageUtil.setterPagedGrid(page, partyBranchList);

    }

    @Override
    public PartyBranch findByPartyBranchName(String partyBranchName) {

        QueryWrapper<PartyBranch> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("party_branch_name", partyBranchName);

        PartyBranch partyBranch = this.getOne(queryWrapper);

        return partyBranch;
    }


}
