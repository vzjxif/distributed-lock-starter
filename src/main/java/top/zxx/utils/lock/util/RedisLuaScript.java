package top.zxx.utils.lock.util;

import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.io.IOException;

public class RedisLuaScript {

    public final static RedisScript<Long> LOCK_SCRIPT = new DefaultRedisScript<>(readLuaScript("lua/lock.lua"), Long.class);

    public final static RedisScript<Long> UNLOCK_SCRIPT = new DefaultRedisScript<>(readLuaScript("lua/unlock.lua"), Long.class);

    public final static RedisScript<Long> TEST_SCRIPT = new DefaultRedisScript<>(readLuaScript("lua/test.lua"), Long.class);

    private static String readLuaScript(String path) {
        try {
            var inputStream = RedisLuaScript.class.getClassLoader().getResourceAsStream(path);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            return new String(bytes);
        } catch (IOException e) {
            throw new RuntimeException("read lua script error, " + path, e);
        }
    }
}
