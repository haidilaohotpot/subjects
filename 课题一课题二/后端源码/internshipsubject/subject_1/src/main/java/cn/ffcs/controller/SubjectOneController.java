package cn.ffcs.controller;

import cn.ffcs.bean.User;
import cn.ffcs.service.UserService;
import cn.ffcs.util.Dom4jUtils;
import cn.ffcs.util.PagedGridResult;
import cn.ffcs.vo.UserVO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dom4j.Document;
import org.dom4j.tree.DefaultDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xiezengcheng
 * @date 2020-08-06
 */
@RestController
@RequestMapping("/user")
public class SubjectOneController {

    @Autowired
    private UserService userService;

    @GetMapping("/pageQuery")
    public JsonResult pageQuery(@RequestParam(required = false,defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "10") int pageSize, String queryText) {
        PagedGridResult pagedGridResult = userService.pageQuery(page, pageSize, queryText);
        return JsonResult.success(pagedGridResult);
    }



    /**
     * 当点击保存的时候，将添加、删除、修改的数据通过JS
     * 封装为xml格式传送到此接口中
     * @param userXmlData
     * @return
     */
    @PostMapping("/put")
    public JsonResult put(String userXmlData){

        List<Map<String, String>> usersMap = Dom4jUtils.parseUsers(userXmlData);

        // 遍历用户
        usersMap.forEach((userMap->{
            ObjectMapper objectMapper = new ObjectMapper();
            UserVO userVO = objectMapper.convertValue(userMap, UserVO.class);
            userService.doService(userVO);

        }));
        return JsonResult.success();
    }

    /**
     * 当点击保存的时候，将添加、删除、修改的数据通过JS
     * 封装为json格式传送到此接口中
     * @param userJsonData
     * @return
     */
    @PostMapping(value = "/doService")
    public JsonResult doService(String userJsonData ) throws JsonProcessingException {

        System.out.println(userJsonData);

        List<UserVO> result = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, UserVO.class);
        result = objectMapper.readValue(userJsonData, javaType);

        // 遍历用户
        result.forEach((userVO->{
            userService.doService(userVO);
        }));

        return JsonResult.success();
    }




}
