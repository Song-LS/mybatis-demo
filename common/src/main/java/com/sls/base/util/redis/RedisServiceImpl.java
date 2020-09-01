package com.sls.base.util.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.time.Duration;

/**
 * @author SLS
 * @data 2019/3/2 13:39
 **/
@Service
public class RedisServiceImpl implements Serializable, RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate1;

    @Override
    public String get(String key) {
        return get(key, String.class);
    }

    @Override
    public void set(String k, String v) {
        this.redisTemplate1.opsForValue().set(k, v, Duration.ofMillis(5000));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(String k, Class<T> cls) {
        Object v = this.redisTemplate1.opsForValue().get(k);
        if (v == null) {
            return null;
        }
        if (v.getClass().isAssignableFrom(cls)) {
            return (T) v;
        }
        throw new IllegalArgumentException();
    }

    @Override
    @SuppressWarnings("all")
    public boolean setNx(String k, Object v, int timeOut) {
        return this.redisTemplate1.opsForValue().setIfAbsent(k, v, Duration.ofMillis(Long.valueOf(timeOut)));
    }

    @Override
    public Object getSet(String k, Object v) {
        return this.redisTemplate1.opsForValue().getAndSet(k, v);
    }

    @Override
    public void del(String k) {
        this.redisTemplate1.delete(k);
    }
}
