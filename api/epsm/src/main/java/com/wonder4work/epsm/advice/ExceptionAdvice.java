package com.wonder4work.epsm.advice;

import com.wonder4work.epsm.domain.Exception;
import com.wonder4work.epsm.service.ExceptionService;
import com.wonder4work.epsm.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
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
    public JSONResult handleException(HttpServletRequest request,Throwable e) {

        String requestURI = request.getServletPath();

        Exception exception = new Exception();
        exception.setCreateTime(new Date());
        exception.setMsg(e.getMessage());
        exception.setUpdateTime(new Date());
        exception.setApi(requestURI);
        exceptionService.save(exception);

        return JSONResult.errorMsg("服务器异常,请联系管理员处理");
    }

}
