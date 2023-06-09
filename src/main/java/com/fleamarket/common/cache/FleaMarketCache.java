package com.fleamarket.common.cache;


import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;

/**
 * 跳蚤市场缓存
 *
 * @author zhuliyou
 * @date 2023/04/28
 */
public class FleaMarketCache {

    private FleaMarketCache() {

    }

    private static final Cache<Object, Object> CACHE = CacheUtil.newFIFOCache(10000);

    public static <K, V> void setCache(K k, V v) {
        CACHE.put(k, v);
    }

    public static <K, V> void setCache(K k, V v, Long time) {
        CACHE.put(k, v, time);
    }

    public static <K, V> V getCache(K k) {
        return (V) CACHE.get(k);
    }
}
