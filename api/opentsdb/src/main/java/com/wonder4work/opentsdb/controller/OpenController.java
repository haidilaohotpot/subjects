package com.wonder4work.opentsdb.controller;

import com.wonder4work.opentsdb.config.OpenTSDBConfiguration;
import com.wonder4work.opentsdb.domain.Open;
import com.wonder4work.opentsdb.util.OpenTSDBUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.assertj.core.util.DateUtil;
import org.opentsdb.client.bean.response.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @author xiezengcheng
 * @date 2020-08-27
 */
@RequestMapping("/open")
@RestController
public class OpenController {

    @Autowired
    private OpenTSDBUtils openTSDBUtils;

    private static final String HOST = "192.168.13.188";
    private static final String TAG_NAME = "host";

    @PostMapping("/create")
    public JSONResult create(@RequestBody Open open) {

        try {
            openTSDBUtils.put(System.currentTimeMillis(), TAG_NAME, open.getHost().toString(), open.getType().toString(), open.getValue().toString());

        } catch (Exception e) {
            return JSONResult.ok();
        }

        return JSONResult.ok();
    }


    @GetMapping("/query")
    public JSONResult query(@RequestParam(required = false) Map<String, Object> queryMap) throws InterruptedException, ExecutionException, IOException {

        PagedGridResult pagedGridResult = new PagedGridResult();

        try {

            List<QueryResult> resultList = openTSDBUtils.query(queryMap.get("startTime").toString(),queryMap.get("endTime").toString(),queryMap.get("type").toString());

            List<Open> opens = new ArrayList<>();
            for (QueryResult queryResult : resultList) {

                LinkedHashMap<Long, Number> dps = queryResult.getDps();
                Set<Long> longs = dps.keySet();

                for (Long aLong : longs) {
                    Open open = new Open();
                    open.setType(queryResult.getMetric());
                    Number number = dps.get(aLong);
                    open.setValue(number);
                    Date date = new Date(aLong * 1000);
                    long l = System.currentTimeMillis();
                    open.setTimestamp(DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
                    opens.add(open);
                }
            }

            pagedGridResult.setRows(opens);

            return JSONResult.ok(pagedGridResult);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return JSONResult.ok(pagedGridResult);
        }


    }


}
