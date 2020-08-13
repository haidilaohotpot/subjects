package cn.ffcs.service.serviceImpl;

import cn.ffcs.bean.User;
import cn.ffcs.mapper.UserMapper;
import cn.ffcs.service.UserService;
import cn.ffcs.util.PagedGridResult;
import cn.ffcs.vo.UserVO;
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

    /**
     * 静态数据库 用于存储用户信息
     */
    private static List<User> userList = new ArrayList<>();

    static {
        User user = new User();
        user.setAge(19);
        user.setCode("999");
        user.setId(1);
        user.setName("BU");
        user.setPosition(1);
        user.setSex(1);

        userList.add(user);

        User user2 = new User();
        user2.setAge(19);
        user2.setCode("999");
        user2.setId(2);
        user2.setName("BU");
        user2.setPosition(1);
        user2.setSex(1);
        userList.add(user2);
    }

    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    @Override
    public void create(UserVO userVO) {
        log.info("**** add ****");
        if (null != userVO) {
            User user = new User();
            BeanUtils.copyProperties(userVO,user);
            userMapper.insert(user);
//            user.setId(userList.size()+1);
//            userList.add(user);
        }
    }

    @Override
    public void update(UserVO userVO) {
        log.info("**** update ****");
        if (null != userVO && null != userVO.getId()) {
            User user = new User();
            BeanUtils.copyProperties(userVO, user);
            userMapper.updateById(user);
            /*userList.removeIf(dbUser->{
                return dbUser.getId().equals(user.getId());
            });
            userList.add(user);*/
        }
    }

    @Override
    public void delete(UserVO userVO) {
        log.info("**** delete ****");
        if (null != userVO && null != userVO.getId()) {
            User user = new User();
            BeanUtils.copyProperties(userVO, user);
            /*userList.removeIf(dbUser->{
                return dbUser.getId().equals(user.getId());
            });*/
            userMapper.deleteById(userVO.getId());
        }
    }

    @Override
    public PagedGridResult pageQuery(int page, int pageSize, String queryText) {
//        return userList;
        // 分页
        PageHelper.startPage(page, pageSize);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotBlank(queryText)) {
            queryWrapper.like("name", queryText);
        }

        List<User> userList = userMapper.selectList(queryWrapper);
        PagedGridResult pagedGridResult = setterPagedGrid(page, userList);
        return pagedGridResult;
    }


    private PagedGridResult setterPagedGrid(Integer page, List<?> list) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }

    @Override
    public void doService(UserVO userVO) {
        log.info("**** put ****");
        if (null != userVO && StringUtils.isNotBlank(userVO.getMethod())) {

            String method = userVO.getMethod();

            switch (method){
                case "add":
                    this.create(userVO);
                    break;
                case "delete":
                    this.delete(userVO);
                    break;
                case "update":
                    this.update(userVO);
                    break;
                default:
                    break;
            }

        }

    }


}
