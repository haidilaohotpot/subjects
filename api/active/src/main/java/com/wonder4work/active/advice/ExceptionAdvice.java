package com.wonder4work.active.advice;

import com.wonder4work.active.domain.Log;
import com.wonder4work.active.service.LogService;
import com.wonder4work.active.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author xiezengcheng
 * @date 2020-09-07
 */
@RestControllerAdvice
public class ExceptionAdvice {

    @Autowired
    private LogService logService;

    @ExceptionHandler(Exception.class)
    public JSONResult handleException(HttpServletRequest request,Exception throwable) {

        Log log = new Log();
        log.setCreateTime(new Date());
        log.setApi(request.getRequestURI());
        log.setContent(throwable.getMessage());
        logService.save(log);
        return JSONResult.errorException(throwable.getMessage());
    }

}
