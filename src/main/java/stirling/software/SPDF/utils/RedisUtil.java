package stirling.software.SPDF.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * @author 29443
 * @date 2022/4/16
 */
@Component
@RequiredArgsConstructor
public class RedisUtil {

    private final RedisTemplate redisTemplate;

    /**
     * 保存hash类型
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public void saveForHash(String key, Object hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 保存普通value类型
     *
     * @param key
     * @param value
     */
    public void saveForValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 保存普通value类型，并设置过期时间
     *
     * @param key
     * @param value
     * @param time
     * @param unit
     */
    public void saveForValueWithExpire(String key, Object value, Long time, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, time, unit);
    }

    /**
     * 保存list
     *
     * @param key
     * @param list
     * @param <T>
     * @return
     */
    public <T> Long saveForList(String key, List<T> list) {
        Long num = redisTemplate.opsForList().rightPushAll(key, list);
        return num == null ? 0 : num;
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param time
     * @param timeUnit
     * @return
     */
    public boolean setExpire(String key, Long time, TimeUnit timeUnit) {
        return redisTemplate.expire(key, time, timeUnit);
    }

    /**
     * 返回过期时间
     *
     * @param key
     * @return
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 获取hashkey的值
     *
     * @param key
     * @param hashKey
     * @param type
     * @param <T>
     * @return
     */
    public <T> T getHashValue(String key, String hashKey, Class<T> type) {
        try {
            T value = (T) redisTemplate.opsForHash().get(key, hashKey);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取普通的value
     *
     * @param key
     * @param <T> 如果存入类型为复杂的泛型，比如List<String>,Array<String> 传参时需要传new
     *     TypeReference<ArrayList<String>>(){}.getType();
     * @return
     */
    public <T> T getValue(String key, Class<T> result) {
        try {
            return (T) redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取list的值
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> List<T> getList(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 查询redis中是否有某个key
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 查询redis中hash类型中是否有某个key
     *
     * @param key
     * @param hashKey
     * @return
     */
    public boolean hasHashKey(String key, Object hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * 刪除key
     *
     * @param key
     * @return
     */
    public boolean deleteKey(String key) {
        return redisTemplate.delete(key);
    }
}
