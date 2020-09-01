package com.sls.base.aspect;

import com.sls.base.anno.RedisLock;
import com.sls.base.util.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author SLS
 * @data 2019/3/5 9:19
 **/
@Aspect
@Component
@Slf4j
public class RedisLockAop {

    @Resource
    private RedisService redisService;

    @Pointcut("@annotation(com.sls.base.anno.RedisLock)")
    private void cut() {

    }

    @Around("cut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result;
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        String key = method.toString();
        RedisLock redisLock = method.getAnnotation(RedisLock.class);
        // 锁超时时间
        int timeout = redisLock.timeout();
        // 锁等待时间
        int waitTime = redisLock.waitTime();
        // 当前时间
        long nowTime = System.currentTimeMillis();
        // 保存时间
        long saveTime = 0L;
        // 插入值得到的状态
        boolean status = redisService.setNx(key, nowTime, timeout);
        log.info("插入状态：" + status);
        while (!status) {
            Long tempSaveTime = redisService.get(key, Long.class);
            // 锁被释放
            if (tempSaveTime == null) {
                status = redisService.setNx(key, nowTime, timeout);
                continue;
            }
            // 锁被重新获取
            if (!tempSaveTime.equals(saveTime)) {
                nowTime = System.currentTimeMillis();
                saveTime = tempSaveTime;
            }
            // 判断是否超时
            if (saveTime + timeout < nowTime) {
                // 超时 直接获取到锁
                Object tempTime = redisService.getSet(key, nowTime);
                if (tempTime == null) {
                    status = redisService.setNx(key, nowTime, timeout);
                    continue;
                }
                // 判断锁是否被释放 或被抢先获取
                if (Objects.equals(saveTime, timeout)) {
                    log.warn("方法：{}， 执行超时，已被强制解锁！", key);
                    break;
                }
            }
            if (waitTime > 0) {
                try {
                    Thread.sleep(waitTime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            status = redisService.setNx(key, nowTime, timeout);
        }
        // 执行
        result = point.proceed();
        // 现在保存的时间
        Long nowSaveTime = redisService.get(key, Long.class);
        // 判断锁未被判断为超时
        if (nowSaveTime != null && Objects.equals(nowSaveTime, nowTime)) {
            // 释放锁
            log.warn("释放锁");
            redisService.del(key);
        }
        return result;
    }

}

