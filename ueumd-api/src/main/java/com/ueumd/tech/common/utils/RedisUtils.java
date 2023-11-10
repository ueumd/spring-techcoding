package com.ueumd.tech.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Description:  redis配置类
 * Author: hsd
 * Date: 2023-06-06 22:36
 */
@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    public void redisKeySerializer(RedisTemplate redisTemplate) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
    }

    /**
     * 设置key，有超时时间，默认为秒
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public boolean set(final String key, Object value, Long expire) {
        try {
            redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 设置key，无超时时间
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        try {
            set(key, value, 7200L);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置key，有超时时间，时间自定义
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public boolean set(final String key, Object value, Long expire, TimeUnit timeUnit) {
        try {
            redisTemplate.opsForValue().set(key, value, expire, timeUnit);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 加锁
     * @param key
     * @param value
     * @return
     */
    public boolean setNx(String key, String value, Long expire) {
        return setNx(key, value, expire, TimeUnit.SECONDS);
    }

    public boolean setNx(String key, String value) {
        return setNx(key, value, 20L);
    }

    public boolean setNx(String key, String value, Long expire, TimeUnit timeUnit) {
        Boolean result = redisTemplate.opsForValue().setIfAbsent(key, value, expire, timeUnit);
        if (result == null || !result) {
            return false;
        }
        return  true;
    }


    public Long increase(String key) {
        return increase(key, 1L);
    }

    public Long increase(String key, Long initValue) {
        return redisTemplate.opsForValue().increment(key, initValue);
    }

    /**
     * 批量删除缓存
     * @param keys
     * @return
     */
    public boolean remove(final String... keys) {
        try {
            for (String key : keys) {
                remove(key);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void remove(String key) {
        if (hasKey(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 获取缓存
     * @param key
     * @return
     */
    public <T> T get(final String key) {
        try {
            T result = (T) redisTemplate.opsForValue().get(key);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void hmset(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 获取list大小
     * @param key
     * @return
     */
    public Long listSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 从右边插入数据
     * @param key
     * @param value
     * @return
     */
    public boolean rightPush(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从左边插入数据
     * @param key
     * @param value
     * @return
     */
    public boolean leftPush(String key, Object value) {
        try {
            redisTemplate.opsForList().leftPush(key, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从右边取数据
     * @param key
     * @return
     */
    public String rightPop(String key) {
        String obj =  (String) redisTemplate.opsForList().rightPop(key);
        return obj;
    }

    /**
     * 从右边取数据
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T rightPop(String key, Class<T> clazz) {
        String obj =  (String) redisTemplate.opsForList().rightPop(key);
        return JSONObject.parseObject(obj, clazz);
    }

    /**
     * 从左边取数据（返回json字符串）
     * @param key
     * @return
     */
    public String leftPop(String key) {
        String obj =  (String) redisTemplate.opsForList().leftPop(key);
        return obj;
    }

    /**
     * 从左边取数据（返回指定类型）
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T leftPop(String key, Class<T> clazz) {
        String obj =  (String) redisTemplate.opsForList().leftPop(key);
        return JSONObject.parseObject(obj, clazz);
    }

    public void lrange(String key, Long start, Long end) {
        redisTemplate.opsForList().range(key, start, end);
    }

    public void add(String key, Object value) {
        redisTemplate.opsForSet().add(key, value);
    }

    public Set setMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public boolean zAdd(String key, Object value, Double source) {
        return redisTemplate.opsForZSet().add(key, value, source);
    }

    public <T> Set<T> rangeByScore(String key, Double source1, Double source2) {
        Set<T> set = redisTemplate.opsForZSet().rangeByScore(key, source1, source2);
        return set;
    }
}
