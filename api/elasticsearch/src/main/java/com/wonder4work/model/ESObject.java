package com.wonder4work.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.List;

/**
 * @author xiezengcheng
 * @date 2020-09-24
 */
@Data
@Document(indexName = "bc_traces", type = "trace")
public class ESObject implements Serializable {

    @Id
    private String id;
    private Object scenarioCode;
    private Object traceId;
    private Object timestamp;
    private Object orderId;
    private Object orderNumber;
    private Object servNo;
    private Object total_time;
}
