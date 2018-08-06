package com.aus.interceptor;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * 处理token 切面
 */
@Aspect
@Configuration
public class ParameterAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(ParameterAspect.class);

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.aus.controller..*.*(..))")
    public void parameter(){
    }

    @Before("parameter()")
    public void doBefore(JoinPoint joinPoint) {
        /**
         * 记录日志
         */
        LOGGER.info("--------------------------------分隔线我最帅----------------------------------");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LOGGER.info("请求地址 : " + request.getRequestURL().toString());
        LOGGER.info("请求方式 : " + request.getMethod());
        LOGGER.info("请求IP : " + request.getRemoteAddr());
        LOGGER.info("路由 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        LOGGER.info("请求参数 : " + JSON.toJSONString(joinPoint.getArgs()));
        startTime.set(System.currentTimeMillis());
    }

    @AfterReturning(returning = "ret", pointcut = "parameter()")
    public void doAfterReturning(Object ret) throws Throwable {
        LOGGER.info("用时(ms) : " + (System.currentTimeMillis() - startTime.get()) + ", 返回结果 : " + JSON.toJSONString(ret));
        LOGGER.info("--------------------------------分隔线我最帅----------------------------------");
    }

}
