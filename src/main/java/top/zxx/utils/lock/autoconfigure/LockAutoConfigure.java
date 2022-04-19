package top.zxx.utils.lock.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import top.zxx.utils.lock.core.RedisLock;

@Configuration
@EnableConfigurationProperties(LockProperties.class)
public class LockAutoConfigure {

    @Bean
    RedisLock getRedisLock(StringRedisTemplate stringRedisTemplate) {
        return new RedisLock(stringRedisTemplate);
    }

}
