package top.zxx.utils.lock.core;

import com.google.common.collect.Lists;
import org.springframework.data.redis.core.StringRedisTemplate;
import top.zxx.utils.lock.Lock;
import top.zxx.utils.lock.util.RedisLuaScript;


public class RedisLock implements Lock {

    private StringRedisTemplate stringRedisTemplate;

    private String prefix;

    private Integer DEFAULT_TIMEOUT = 10;

    public RedisLock(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean tryLock(String key, String owner) {
        return this.tryLock(key, owner, DEFAULT_TIMEOUT);
    }

    @Override
    public boolean tryLock(String key, String owner, Integer timeout) {
        return stringRedisTemplate.execute(RedisLuaScript.LOCK_SCRIPT, Lists.newArrayList(key), owner, String.valueOf(timeout)) > 0;
    }

    @Override
    public boolean unLock(String key, String owner) {
        Long execute = stringRedisTemplate.execute(RedisLuaScript.UNLOCK_SCRIPT, Lists.newArrayList(key), owner);
        return execute > 0;
    }
}
