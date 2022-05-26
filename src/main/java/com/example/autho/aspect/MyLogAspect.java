package com.example.autho.aspect;

import com.alibaba.fastjson.JSONObject;
import com.example.autho.annotation.MyLog;
import com.example.autho.myconfig.MyEx;
import com.example.autho.myconfig.MyRuntimeEx;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author: code-zl
*/
@Aspect //AOP 切面
@Component
@Slf4j
public class MyLogAspect {


    //切入点
    @Pointcut(value = "@annotation(com.example.autho.annotation.MyLog)")
    private void pointcut() {

    }


    /**
     * 在方法执行前后
     *
     * @param point
     * @param myLog
     * @return
     */
    @Around(value = "pointcut() && @annotation(myLog)")
    public Object around(ProceedingJoinPoint point, MyLog myLog) {

        System.out.println("++++执行了around方法++++");

        String requestUrl = myLog.requestUrl();

        //拦截的类名
        Class clazz = point.getTarget().getClass();
        //拦截的方法
        Method method = ((MethodSignature) point.getSignature()).getMethod();

        //利用方法参数获取request请求头，必须在方法参数中包含
        Object[] args = point.getArgs();
        for (Object arg:args) {
            if (arg instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest) arg;
                String head = request.getHeader("name");
                System.out.println(head);
            }
        }
        System.out.println("mylog的值"+myLog.value()+"|请求的url"+myLog.requestUrl()+"---"+myLog.path());

        System.out.println("执行了 类:" + clazz + " 方法:" + method + " 自定义请求地址:" + requestUrl);

        try {
            return point.proceed(); //执行程序
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return throwable.getMessage();
        }
    }

    /**
     * 方法执行后
     *
     * @param joinPoint
     * @param myLog
     * @param result
     * @return
     */
    @AfterReturning(value = "pointcut() && @annotation(myLog)", returning = "result")
    public Object afterReturning(JoinPoint joinPoint, MyLog myLog, Object result) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
//
//        HttpSession session = request.getSession();

        if (request.getHeader("name")!=null) {
            String name = request.getHeader("name");
            if (name.equals("zl")) {
                System.out.println("我的请求头"+name);
            }else {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("msg","你好，异常");
                jsonObject.put("code",502);

                assert response != null;
                response.reset();
                response.setCharacterEncoding("UTF-8");
                response.setContentType("Content-Type: application/json;charset:UTF-8");
                try (PrintWriter pw = response.getWriter()) {
                    pw.write(String.valueOf(jsonObject));
                    pw.flush();
                    //pw.close();
                } catch (Exception e) {
                    log.info(e.getMessage());
                }
                //try {
                //    pw.write(String.valueOf(jsonObject));
                //    pw.flush();
                //    pw.close();
                //}catch (Exception e) {
                //    log.info(e.getMessage());
                //}finally {
                //    pw.close();
                //}
                //throw new MyRuntimeEx("404","异常");
            }
        }

        System.out.println("++++执行了afterReturning方法++++");

        System.out.println("执行结果：" + result);

        return result;
    }

    /**
     * 方法执行后 并抛出异常
     *
     * @param joinPoint
     * @param myLog
     * @param ex
     */
    @AfterThrowing(value = "pointcut() && @annotation(myLog)", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, MyLog myLog, Exception ex) {
        System.out.println("++++执行了afterThrowing方法++++");
        System.out.println("请求：" + myLog.requestUrl() + " 出现异常");
    }

    private HttpServletRequest currentRequest() {
        // Use getRequestAttributes because of its return null if none bound
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return Optional.ofNullable(servletRequestAttributes).map(ServletRequestAttributes::getRequest).orElse(null);
    }

    private HttpServletResponse currentResponse() {
        // Use getRequestAttributes because of its return null if none bound
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return Optional.ofNullable(servletRequestAttributes).map(ServletRequestAttributes::getResponse).orElse(null);
    }
}