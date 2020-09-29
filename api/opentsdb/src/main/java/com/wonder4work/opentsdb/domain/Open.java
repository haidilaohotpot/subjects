package com.wonder4work.opentsdb.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author xiezengcheng
 * @date 2020-08-27
 */
@Data
public class Open {


    private Object host = "192.168.13.188";

    private Object type;

    private Object timestamp;

    private Object value;


}
