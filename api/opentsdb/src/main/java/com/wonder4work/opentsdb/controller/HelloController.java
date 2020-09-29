package com.wonder4work.opentsdb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiezengcheng
 * @date 2020-08-27
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public JSONResult hello() {
        return JSONResult.ok();
    }

}
