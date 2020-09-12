package com.wonder4work.active.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wonder4work.active.domain.Activity;
import com.wonder4work.active.domain.Comment;
import com.wonder4work.active.vo.ActivityVO;
import com.wonder4work.active.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wonder4work
 * @since 2020-09-07
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 根据ID 查询
     * @param id
     * @return
     */
    CommentVO findById(@Param("id") Integer id);


    /**
     * 评论
     * @param map
     */
    void comment(@Param("map") Map<String, Object> map);


    /**
     * 条件查询评论信息
     * @param map 查询参数
     * @return List<CommentVO>
     */
    List<CommentVO> query(@Param("map") Map<String, Object> map);

    /**
     * 根据ID删除
     * @param id 评论ID
     * @param activityId 活动ID
     */
    void delById(@Param("id") Integer id,@Param("activityId") Integer activityId);
}
