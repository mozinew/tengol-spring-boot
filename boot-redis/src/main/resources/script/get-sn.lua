--
-- Created by IntelliJ IDEA.
-- User: dongrui
-- Date: 2021/5/26 15:07
-- 生成序列号，每调用一次则加 1
--

--根据key判断是否存在
local key = redis.call("HEXISTS", KEYS[1], KEYS[2])
--存在key
if tonumber(key) == 1 then
    --获取key的值
    local number = redis.call("HGET", KEYS[1], KEYS[2])
    --key的值小于阈值
    if tonumber(number) < tonumber(ARGV[1]) then
        redis.call("HINCRBY", KEYS[1], KEYS[2], tonumber(ARGV[2]))
        local new_value = redis.call("HGET", KEYS[1], KEYS[2])
        return tonumber(new_value)
    else
        return 1
    end

else
    --不存在
    redis.call("HSET", KEYS[1], KEYS[2], 1)
    return 1
end