package top.zxx.utils.lock

import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate
import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Title
import top.zxx.utils.lock.core.RedisLock

@Title("lock case")
class LockTest extends Specification {

    def redisStandaloneConfiguration = new RedisStandaloneConfiguration()
    def lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration)
    def redisClient = new StringRedisTemplate(lettuceConnectionFactory)


    def "lock case"() {
        given:
        lettuceConnectionFactory.afterPropertiesSet()
        def lock = new RedisLock(redisClient)
        expect:
        lock.tryLock("abc_test", "test")
        !lock.tryLock("abc_test", "test2")
        !lock.unLock("abc_test", "test2")
        lock.unLock("abc_test", "test")
    }

    @Ignore
    def "unlock case"() {
        given:
        lettuceConnectionFactory.afterPropertiesSet()
        def lock = new RedisLock(redisClient)
        expect:
        lock.unLock("abc_test", "test")
    }
}
