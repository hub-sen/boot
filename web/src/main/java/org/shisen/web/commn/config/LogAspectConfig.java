package org.shisen.web.commn.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shisen
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class LogAspectConfig {

    private final ObjectMapper objectMapper;

    @Autowired
    public LogAspectConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Pointcut("execution(* org.shisen.web.controller..*.*(..))")
    private void controllerAspect() {
    }


    @Before(value = "controllerAspect()")
    public void beforeHandle(JoinPoint joinPoint) throws JsonProcessingException, NullPointerException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new NullPointerException("request is null");
        }
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("========================= REQUEST CONTENT START =========================");
        log.info("request url : {}", request.getRequestURL().toString());
        log.info("request method : {}", request.getMethod());
        log.info("request header : {}", objectMapper.writeValueAsString(getRequestHeaderMap(request)));
        Object[] argumentArray = removeResponseFromArguments(joinPoint.getArgs());
        log.info("request param : {}", objectMapper.writeValueAsString(argumentArray));
        log.info("========================= REQUEST CONTENT END ===========================");
    }


    @AfterReturning(returning = "response", value = "controllerAspect()")
    public void afterHandle(Object response) throws JsonProcessingException {
        log.info("========================= RESPONSE CONTENT START ========================");
        log.info("response content : {}", objectMapper.writeValueAsString(response));
        log.info("========================= RESPONSE CONTENT END ==========================");

    }


    private Map<String, String> getRequestHeaderMap(HttpServletRequest request) {
        Map<String, String> header = new HashMap<>(16);
        Enumeration enums = request.getHeaderNames();
        while (enums.hasMoreElements()) {
            String headerParam = (String) enums.nextElement();
            header.put(headerParam, request.getHeader(headerParam));
        }
        return header;
    }

    private Object[] removeResponseFromArguments(Object[] argumentArray) {
        if (ArrayUtils.isEmpty(argumentArray)) {
            return null;
        }
        for (Object obj : argumentArray) {
            if (obj instanceof HttpServletResponse) {
                argumentArray = ArrayUtils.removeElement(argumentArray, obj);
                break;
            }
        }
        return argumentArray;
    }

}