package cn.fleamarket.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


/**
 * redis工具类
 *
 * @author zhuliyou
 * @date 2021/04/30
 */
@Component
public class RedisUtil<K, V> {

    @Resource
    private RedisTemplate<K, V> redisTemplate;


    public V get(K key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void set(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }


    public void set(K key, V value, Long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.MINUTES);
    }

    public void remove(K key) {
        redisTemplate.delete(key);
    }
}
