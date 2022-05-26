package com.example.autho.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class TransactionalAspect {

    @Around(value = "@annotation(com.example.autho.aop.MeTransactional)")
    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            //目标方法
            log.info(">>>开启事务");
            Object result = joinPoint.proceed();
            log.info("提交事务");
            return result;
        } catch (Throwable throwable) {
            log.info("回滚");
            throwable.printStackTrace();
            return "错误";
        }
    }
}
