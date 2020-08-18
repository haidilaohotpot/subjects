package cn.ffcs.service.serviceImpl;

import cn.ffcs.bean.User;
import cn.ffcs.bo.UserBO;
import cn.ffcs.mapper.UserMapper;
import cn.ffcs.service.UserService;
import cn.ffcs.util.PagedGridResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiezengcheng
 * @date 2020-08-06
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    @Override
    public void create(UserBO userBO) {
        log.info("**** add:{} ****", userBO);

        User user = new User();
        BeanUtils.copyProperties(userBO,user);
        userMapper.insert(user);
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    @Override
    public void update(UserBO userBO) {
        log.info("**** update:{} ****", userBO);
        User dbUser = userMapper.selectById(userBO.getId());
        if (null != dbUser) {
            User user = new User();
            BeanUtils.copyProperties(userBO, user);
            user.setCode(dbUser.getCode());
            userMapper.updateById(user);
        }

    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    @Override
    public void delete(Integer id) {
        log.info("**** delete:{} ****", id);
        userMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult pageQuery(int page, int pageSize, String queryText) {
        // 分页
        PageHelper.startPage(page, pageSize);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotBlank(queryText)) {
            queryWrapper.like("name", queryText);
        }

        queryWrapper.orderByDesc("id");

        List<User> userList = userMapper.selectList(queryWrapper);
        return setterPagedGrid(page, userList);
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.SUPPORTS)
    @Override
    public boolean checkCode(String code) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code",code);
        Integer count = userMapper.selectCount(queryWrapper);
        return code != null && count > 0;
    }


    /**
     * 封装分页参数
     * @param page
     * @param list
     * @return
     */
    private PagedGridResult setterPagedGrid(Integer page, List<?> list) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }

}
