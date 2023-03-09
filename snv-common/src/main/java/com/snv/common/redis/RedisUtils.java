package com.snv.common.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Joword
 * @date: 2023/3/9 18:13
 * @version: 1.0
 * @description: Redis implements
 */
@Component
@RequiredArgsConstructor
public class RedisUtils {

    public final RedisTemplate redisTemplate;

    /**
     * redis primary object，include Integer, String, Bean...
     *
     * @param key   String
     * @param value T
     * @param <T>   pojo
     *
     * @return redis object
     */
    public <T> ValueOperations<String, T> setRedisObject(String key, T value) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
        return valueOperations;
    }

    /**
     * redis primary object, another argments: timeout. include Integer, String, Bean...
     *
     * @param key      String
     * @param value    T
     * @param timeout  Integer, time durable
     * @param timeUnit time unit
     * @param <T>      pojo
     *
     * @return redis object
     */
    public <T> ValueOperations<String, T> setRedisObject(String key, T value, Integer timeout, TimeUnit timeUnit) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value, timeout, timeUnit);
        return valueOperations;
    }

    /**
     * to get a KV object
     *
     * @param key String
     * @param <T> pojo
     *
     * @return java entity
     */
    public <T> T getRedisObject(String key) {
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * to delete redis a KV
     *
     * @param key String
     */
    public void deleteRedisObject(String key) {
        redisTemplate.delete(key);
    }

    /**
     * to delete a set object of redis
     *
     * @param collection Set or Lists
     */
    public void deleteObject(Collection collection) {
        redisTemplate.delete(collection);
    }

    /**
     * to get redis list
     *
     * @param key String
     * @param <T> pojo
     *
     * @return java Lists collection
     */
    public <T> List<T> getRedisList(String key) {
        List<T> lists = new ArrayList<>();
        ListOperations<String, T> listOperations = redisTemplate.opsForList();
        for (int i = 0; i < listOperations.size(key); i++) {
            lists.add(listOperations.index(key, i));
        }
        return lists;
    }

    /**
     * to set redis list
     *
     * @param key   String
     * @param lists a set of datafracture
     * @param <T>   pojo
     *
     * @return java Set Object
     */
    public <T> ListOperations<String, T> setRedisList(String key, List<T> lists) {
        ListOperations listOperations = redisTemplate.opsForList();
        if (lists != null) {
            for (int i = 0; i < lists.size(); i++) {
                listOperations.leftPush(key, lists.get(i));
            }
        }
        return listOperations;
    }

    /**
     * to get redis Lists KV
     *
     * @param key String
     * @param <T> pojo
     *
     * @return java unsorted Lists
     */
    public <T> Set<T> getRedisSet(String key) {
        Set<T> set = new HashSet<>();
        BoundSetOperations<String, T> boundSetOperations = redisTemplate.boundSetOps(key);
        set = boundSetOperations.members();
        return set;
    }

    /**
     * to set redis cache
     *
     * @param key  String
     * @param maps Set<T>
     * @param <T>  pojo
     *
     * @return java
     */
    public <T> BoundSetOperations<String, T> setRedisSet(String key, Set<T> maps) {
        BoundSetOperations<String, T> boundSetOperations = redisTemplate.boundSetOps(key);
        Iterator<T> iterator = maps.iterator();
        while (iterator.hasNext()) {
            boundSetOperations.add(iterator.next());
        }
        return boundSetOperations;
    }

    /**
     * to get redis map
     *
     * @param key String
     * @param <T> pojo
     *
     * @return java key-value
     */
    public <T> Map<String, T> getRedisMap(String key) {
        Map<String, T> map = redisTemplate.opsForHash().entries(key);
        return map;
    }

    /**
     * to set redis hash object
     *
     * @param key String
     * @param map Map<String, T>
     * @param <T> pojo
     *
     * @return redis hash object
     */
    public <T> HashOperations<String, String, T> setRedisMap(String key, Map<String, T> map) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        if (map != null) {
            for (Map.Entry<String, T> entry : map.entrySet()) {
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
        }
        return hashOperations;
    }

    /**
     * to get redis argument lists
     *
     * @param patterns carnonical form
     *
     * @return String Set
     */
    public Collection<String> keys(String patterns) {
        return redisTemplate.keys(patterns);
    }
}
