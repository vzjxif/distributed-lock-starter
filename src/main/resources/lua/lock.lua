local lockKey = KEYS[1]
local reentrantKey = lockKey .. "_reentrant"
local lockTid = ARGV[1]
local timeout = ARGV[2]
local result = 0
--设置当前线程
if redis.call("setnx", lockKey, lockTid) == 1 then
    --    set expire time
    redis.call("expire", lockKey, timeout)
    redis.call("incr", reentrantKey)
    redis.call("expire", reentrantKey, timeout)
    return 1
else
    -- 重入
    if redis.call("get", lockKey) == lockTid then
        redis.call("incr", reentrantKey)
        return 1
    end
end

return result