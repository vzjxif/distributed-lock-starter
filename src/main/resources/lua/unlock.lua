---
--- Generated by Luanalysis
--- Created by vzjxif.
--- DateTime: 2022/4/15 5:34 PM
---
local lockKey = KEYS[1]
local reentrantKey = lockKey .. "_reentrant"
local lockTid = ARGV[1]
local result = 0

if redis.call("get", lockKey) == lockTid then
    --    set expire time
    result = redis.call("decr", reentrantKey)
    if result == 0 then
        redis.call("del", reentrantKey)
        redis.call("del", lockKey)
    end
    return 1
end
return 0
