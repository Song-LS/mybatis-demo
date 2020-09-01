package com.sls.base.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author SLS
 * @data 2019/3/5 9:22
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisLock {

    // 超时时间， 自动释放锁的时间， 单位：毫秒
    int timeout() default 2000;

    // 等待时间， 单位：毫秒
    int waitTime() default 50;
}