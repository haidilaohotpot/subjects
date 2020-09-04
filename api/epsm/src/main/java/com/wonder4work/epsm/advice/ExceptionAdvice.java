package com.wonder4work.epsm.advice;

import com.wonder4work.epsm.domain.Exception;
import com.wonder4work.epsm.service.ExceptionService;
import com.wonder4work.epsm.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

/**
 * @author xiezengcheng
 * @date 2020-09-01
 */
@ControllerAdvice
public class ExceptionAdvice {

    @Autowired
    private ExceptionService exceptionService;

    @ExceptionHandler(value = Throwable.class)
    public JSONResult handleException(Throwable e) {

        Exception exception = new Exception();
        exception.setCreateTime(new Date());
        exception.setMsg(e.getMessage());
        exception.setUpdateTime(new Date());
        exceptionService.save(exception);

        return JSONResult.errorMsg("服务器异常,请联系管理员处理");
    }

}
