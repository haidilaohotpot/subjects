package cn.ffcs.util;

import cn.ffcs.bean.User;
import cn.ffcs.service.UserService;
import cn.ffcs.service.serviceImpl.UserServiceImpl;
import cn.ffcs.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * dom4j 解析xml工具类
 * @author xiezengcheng
 * @date 2020-08-06
 */
@Slf4j
public class Dom4jUtils {

    /**
     * String转Document
     * @param xml
     * @return
     */
    public static Document getDocument(String xml){
        Document document = null;
        try {
            SAXReader reader = new SAXReader();
            InputStream in = new ByteArrayInputStream(xml.getBytes("utf-8"));
            InputStreamReader strInStream = new InputStreamReader(in, "utf-8");
            document = reader.read(strInStream);
        } catch (Exception e) {
            log.error("String转Document失败，{}", e.getMessage());
        }
        return document;
    }

    /**
     * 递归解析
     * @param node
     * @param map
     */
    public static void nodesToMap(Element node,Map<String,String> map) {
        if (!(node.getTextTrim().equals(""))) {
            map.put(node.getName(), node.getText());
        }
        Iterator<Element> it = node.elementIterator();
        while (it.hasNext()){
            Element el = it.next();
            nodesToMap(el,map);
        }
    }

    /**
     * 无脑解析
     * @param xml
     * @return
     */
    public static Map<String,String> getMap(String xml){
        Document document  =Dom4jUtils.getDocument(xml);
        Element node = document.getRootElement();
        Map<String,String> map = new HashMap<String,String>();
        nodesToMap(node,map);
        return map;
    }

    /**
     * 解析多个相同兄弟节点
     * @param elements
     * @return
     */
    public static List<Map<String,String>> nodeToList(Element elements ){
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        List<Element> childElements = elements.elements();
        for (Element element : childElements) {
            Map<String,String> map = new HashMap<String,String>();
            nodesToMap(element , map);
            list.add(map);
        }
        return list;
    }

    /**
     * 解析用户参数
     * @param xml
     * @return
     */
    public static List<Map<String,String>> parseUsers(String xml){
        Document document  =Dom4jUtils.getDocument(xml);
        Element root = document.getRootElement();
        Element usersElement = root.element("users");
        return nodeToList(usersElement);
    }





    public static void main(String[] args) {

        String xmlData = "<root><users><user><method>add</method><id>1</id><name>JJ</name></user></users></root>";

        List<Map<String, String>> maps = Dom4jUtils.parseUsers(xmlData);

        System.out.println(maps);
        UserService userService = new UserServiceImpl();

        // 遍历用户
        maps.forEach((userMap->{
            ObjectMapper objectMapper = new ObjectMapper();
            UserVO userVO = objectMapper.convertValue(userMap, UserVO.class);
            userService.doService(userVO);

        }));



    }

}
