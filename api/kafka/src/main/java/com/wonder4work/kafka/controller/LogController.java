package com.wonder4work.kafka.controller;

import com.wonder4work.kafka.domain.Log;
import com.wonder4work.kafka.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiezengcheng
 * @date 2020-08-25
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/query")
    public JSONResult query(@RequestParam String queryText,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer pageSize) {

        PagedGridResult pagedGridResult = logService.query(queryText, page, pageSize);

        return JSONResult.ok(pagedGridResult);
    }


    @PostMapping("/create")
    public JSONResult create(@RequestBody String logList) {

        log.info(logList);
        List<Log> logs = JsonUtils.jsonToList(logList, Log.class);
        if (logs.size() > 0) {
            logs.forEach(log -> {
                logService.create(log);
            });
        }
        return JSONResult.ok();
    }

}
