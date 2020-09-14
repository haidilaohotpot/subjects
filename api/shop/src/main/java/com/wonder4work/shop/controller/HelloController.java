package com.wonder4work.shop.controller;

import com.wonder4work.shop.utils.JSONResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author xiezengcheng
 * @date 2020-09-14
 */
@ApiIgnore
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public JSONResult hello() {
        return JSONResult.ok("Hello g.xiezch@ffcs");
    }

}
