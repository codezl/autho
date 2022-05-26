package com.example.autho.aspect;

import com.alibaba.fastjson.JSONObject;
import com.example.autho.annotation.KthLog;
import com.example.autho.myconfig.MyRuntimeEx;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@Aspect
public class KthLogAspect {

    @Pointcut("@annotation(com.example.autho.annotation.KthLog)")
    private void pointcut() {}

    @Before("pointcut() && @annotation(logger)")
    public void advice(JoinPoint joinPoint, KthLog logger) {
        System.out.println("--- Kth日志的内容为[" + logger.value() + "] ---");

        joinPoint.getSignature();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
//        HttpSession session = request.getSession();

            if (request.getHeader("name")!=null) {
                String name = request.getHeader("name");
                if (name.equals("zl")) {
                    System.out.println("我的请求头" + name);
                } else {
                    Map<String, Object> res = new HashMap();
                    res.put("msg", "请求头不正确");
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("msg", "你好，异常");
                    throw new MyRuntimeEx("404", "异常");
                }
            }
    }
}