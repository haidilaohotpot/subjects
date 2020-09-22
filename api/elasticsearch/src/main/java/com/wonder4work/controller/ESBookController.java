package com.wonder4work.controller;

import com.wonder4work.model.Book;
import com.wonder4work.model.ElasticEntity;
import com.wonder4work.util.ESUtils;
import com.wonder4work.util.JsonResult;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author xiezengcheng
 * @date 2020-08-19
 */
@RestController
@RequestMapping("/es/book")
public class ESBookController {

    @GetMapping("/isExit")
    public Object isExit(@RequestParam String indexName) throws Exception {
        if (StringUtils.isEmpty(indexName)) {
            return JsonResult.failed("索引名不能为空");
        }
        return ESUtils.isExistsIndex(indexName);
    }


    @PostMapping("/createOrUpdate")
    public Object createOrUpdate(@RequestParam String indexName, @RequestParam String id, @RequestBody Map<String,Object> map) {
        if (StringUtils.isEmpty(indexName)) {
            return JsonResult.failed("索引名不能为空");
        }
        ElasticEntity<Book> elasticEntity = new ElasticEntity();
        elasticEntity.setId(id);
        elasticEntity.setData(map);
        ESUtils.insertOrUpdateOne(indexName, id, new HashMap<>());
        return JsonResult.success();
    }


    @PutMapping("/delete/{indexName}/{id}")
    public JsonResult delete(@PathVariable(required = true) String indexName, @PathVariable(required = true) String id) {
        ESUtils.deleteOne(indexName, id);
        return JsonResult.success();
    }

    @GetMapping("/query")
    public JsonResult query(@RequestParam String indexName) {

        if (StringUtils.isEmpty(indexName)) {
            return JsonResult.failed("索引名不能为空");
        }
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        List<Book> bookList = ESUtils.search(indexName, searchSourceBuilder,Book.class);
        return JsonResult.success(bookList);
    }


    @GetMapping("/createIndex")
    public Object createIndex(@RequestParam String indexName) {
        if (StringUtils.isEmpty(indexName)) {
            return JsonResult.failed("索引名不能为空");
        }
        ESUtils.createIndex(indexName, new HashMap<>());
        return JsonResult.success();
    }


}
