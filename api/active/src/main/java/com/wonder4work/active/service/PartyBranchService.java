package com.wonder4work.active.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wonder4work.active.domain.PartyBranch;
import com.wonder4work.active.utils.PagedGridResult;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wonder4work
 * @since 2020-09-07
 */
public interface PartyBranchService extends IService<PartyBranch> {

    /**
     * 分页查询党支部信息
     * @param queryMap
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult query(Map<String, Object> queryMap, Integer page, Integer pageSize);

    /**
     * 根据党支部名称查询
     * @param partyBranchName
     * @return
     */
    PartyBranch findByPartyBranchName(String partyBranchName);

}
