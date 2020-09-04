package com.wonder4work.kafka.acpect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author xiezengcheng
 * @date 2020-08-26
 */
@Slf4j
@Aspect
@Component
public class LogAspect {


    /**
     * 切面表达式
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.wonder4work.kafka.service.serviceImpl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("===== 开始执行 {}.{} =====",
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName());

        String msg = joinPoint.getSignature().getName();
        // 开始时间
        long begin = System.currentTimeMillis();

        // 执行
        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();

        // 时间差
        long takeTime = end - begin;

        log.info("===== 开始结束 {}.{} =====",
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName() + "耗时：" + takeTime);

        return result;
    }


}
