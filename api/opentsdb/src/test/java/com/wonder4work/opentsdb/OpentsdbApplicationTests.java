package com.wonder4work.opentsdb;

import com.wonder4work.opentsdb.domain.Open;
import com.wonder4work.opentsdb.util.OpenTSDBUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.opentsdb.client.bean.response.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
class OpentsdbApplicationTests {


    @Autowired
    private OpenTSDBUtils openTSDBUtils;

    @Test
    void contextLoads() throws InterruptedException, ExecutionException, IOException {


        List<QueryResult> resultList = openTSDBUtils.query(System.currentTimeMillis(),System.currentTimeMillis()+100 ,"cpu");

        List<Open> opens = new ArrayList<>();
        for (QueryResult queryResult : resultList) {


            log.info(queryResult.getMetric());
            log.info(queryResult.getTags().get("host"));
            LinkedHashMap<Long, Number> dps = queryResult.getDps();


            Set<Long> longs = dps.keySet();

            for (Long aLong : longs) {
                Open open = new Open();
                open.setHost(queryResult.getAggregateTags().get(0));
                open.setType(queryResult.getMetric());
                Number number = dps.get(aLong);
                open.setValue(number);
                open.setTimestamp(aLong);
                opens.add(open);
            }
        }


        log.info(opens.toString());
    }




}
