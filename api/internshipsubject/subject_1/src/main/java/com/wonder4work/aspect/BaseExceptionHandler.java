package com.wonder4work.aspect;

import com.wonder4work.controller.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xiezengcheng
 * @date 2020-08-17
 */
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public JsonResult handleBaseException() {
        return JsonResult.failed("服务器异常请联系管理员处理");
    }

}
