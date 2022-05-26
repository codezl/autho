package com.example.autho.aspect;

import com.example.autho.annotation.UserAnnotation;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserAspect {

    @Pointcut("@annotation(com.example.autho.annotation.UserAnnotation)")
    private void pointcut() {}

    @Before("pointcut() && @annotation(log)")
    public void advice(UserAnnotation log) {
        System.out.println("2111");
        System.out.println("---log为"+log.value()+"---");
    }
}
