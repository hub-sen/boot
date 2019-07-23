package com.pengfeng.limit.annotation;

import java.lang.annotation.*;

/**
 * 分布式限流器
 * pengfeng
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Limiter {

    /**
     * 限流key
     * @return
     */
    String key() default "rate:limiter";
    /**
     * 单位时间限制通过请求数
     * @return
     */
    long limit() default 10;

    /**
     * 过期时间，单位秒
     * @return
     */
    long expire() default 1;

    /**
     * 限流提示语
     * @return
     */
    String message() default "false";
}
