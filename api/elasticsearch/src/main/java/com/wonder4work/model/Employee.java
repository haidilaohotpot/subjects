package com.wonder4work.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author xiezengcheng
 * @date 2020-08-20
 */
@Data
public class Employee {

        private String number;

        private Double price;

        private String title;

        private String province;

        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date publishTime;

}
