package com.wonder4work.epsm.advice;

import com.wonder4work.epsm.domain.InterfaceLog;
import com.wonder4work.epsm.service.InterfaceLogService;
import com.wonder4work.epsm.utils.DateUtil;
import com.wonder4work.epsm.utils.JSONResult;
import com.wonder4work.epsm.utils.JsonUtils;
import com.wonder4work.epsm.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author xiezengcheng
 * @date 2020-09-02
 */
@Aspect
@Component
@Slf4j
public class LogAdvice {

    @Autowired
    private InterfaceLogService interfaceLogService;

    /**
     * 切面表达式
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.wonder4work.epsm.controller..*.*(..))")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Date start = new Date();
        Object[] args = joinPoint.getArgs();
        String params = "";
        if (args != null && args.length > 0) {
            log.info("----请求参数,{}----", JsonUtils.objectToJson(args[0]));
            params = JsonUtils.objectToJson(args[0]);
        }
        JSONResult result = new JSONResult();
        InterfaceLog interfaceLog = new InterfaceLog();
        String response = joinPoint.getTarget().getClass()+" ("
                +joinPoint.getSignature().getName() +")";
        try{
            interfaceLog.setRequestStatus(HttpStatus.OK.value());
            interfaceLog.setRequestParams(params);
            interfaceLog.setResponseContent(response);
            interfaceLog.setCreateTime(start);
            result = (JSONResult) joinPoint.proceed();
            interfaceLog.setResponseStatus(result.getStatus());
            log.info("---- 返回结果,{}----", JsonUtils.objectToJson(result));
        }catch (Exception e){
            log.info("---- 异常,{}----", e.getMessage());
            interfaceLog.setResponseStatus(HttpStatus.EXPECTATION_FAILED.value());
        }
        interfaceLog.setUpdateTime(new Date());
        interfaceLogService.save(interfaceLog);
        return result;
    }


}
