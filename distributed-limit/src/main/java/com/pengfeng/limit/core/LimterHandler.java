package com.pengfeng.limit.core;

import com.google.common.base.Preconditions;
import com.pengfeng.limit.annotation.Limiter;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pengfeng
 */

@Aspect
@Component
public class LimterHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LimterHandler.class);

    @Autowired
    RedisTemplate redisTemplate;

    private DefaultRedisScript<Long> getRedisScript;

    @PostConstruct
    public void init() {
        getRedisScript = new DefaultRedisScript<>();
        getRedisScript.setResultType(Long.class);
        getRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("limter.lua")));
        LOGGER.info("limterHandler[分布式限流处理器]脚本加载完成");
    }

    @Pointcut("@annotation(com.pengfeng.limit.annotation.Limiter)")
    public void limiter() {
    }

    @Around("@annotation(limiter)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, Limiter limiter) throws Throwable {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("limterHandler[分布式限流处理器]开始执行限流操作");
        }
        Signature signature = proceedingJoinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("the Annotation @limter must used on method!");
        }
        /**
         * 获取注解参数
         */
        // 限流模块key
        String limitKey = limiter.key();
        Preconditions.checkNotNull(limitKey);
        // 限流阈值
        long limitTimes = limiter.limit();
        // 限流超时时间
        long expireTime = limiter.expire();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("limterHandler[分布式限流处理器]参数值为-limitTimes={},limitTimeout={}", limitTimes, expireTime);
        }
        // 限流提示语
        String message = limiter.message();
        if (StringUtils.isBlank(message)) {
            message = "false";
        }
        /**
         * 执行Lua脚本
         */
        List<String> keyList = new ArrayList();
        // 设置key值为注解中的值
        keyList.add(limitKey);
        /**
         * 调用脚本并执行
         */
        Long result = (Long) redisTemplate.execute(getRedisScript, keyList, expireTime, limitTimes);
        if (result == 0) {
            String msg = "由于超过单位时间=" + expireTime + "-允许的请求次数=" + limitTimes + "[触发限流]";
            LOGGER.debug(msg);
            return message;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("limterHandler[分布式限流处理器]限流执行结果-result={},请求[正常]响应", result);
        }
        return proceedingJoinPoint.proceed();
    }
}