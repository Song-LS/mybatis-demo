package com.sls.base.util.redis;

/**
 * @author SLS
 * @data 2019/3/2 13:39
 * <p>
 * redis 服务
 **/
public interface RedisService {

    /**
     * 通过key 拿到redis 的值
     *
     * @param key redis 的key
     * @return
     */
    String get(String key);

    /**
     * 往redis 设置值
     *
     * @param k 键
     * @param v 值
     */
    void set(String k, String v);

    /**
     * 拿到泛型
     *
     * @param k
     * @param cls 转换为那个类
     * @param <T> 泛型
     * @return 类
     */
    <T> T get(String k, Class<T> cls);

    /**
     * 如果不存在则插入
     *
     * @param k
     * @param v
     * @param timeOut 超时时间
     * @return boolean
     */
    boolean setNx(String k, Object v, int timeOut);

    /**
     * 拿到并设置一个新值
     *
     * @param k
     * @param v
     * @return
     */
    Object getSet(String k, Object v);

    /**
     * 删除一个
     *
     * @param k
     */
    void del(String k);

}
