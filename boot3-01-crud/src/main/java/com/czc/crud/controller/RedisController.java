package com.czc.crud.controller;

import com.czc.crud.common.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/api/redis")
@RequiredArgsConstructor
public class RedisController {

    private final StringRedisTemplate redisTemplate;

    @PostMapping("/string")
    public Result<Void> setString(@RequestParam String key,
                                  @RequestParam String value,
                                  @RequestParam(defaultValue = "0") long ttlSeconds) {
        if (ttlSeconds > 0) {
            redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(ttlSeconds));
        } else {
            redisTemplate.opsForValue().set(key, value);
        }
        return Result.ok(null);
    }

    @GetMapping("/string")
    public Result<String> getString(@RequestParam String key) {
        return Result.ok(redisTemplate.opsForValue().get(key));
    }

    @PostMapping("/hash")
    public Result<Void> setHash(@RequestParam String key,
                                @RequestParam String field,
                                @RequestParam String value) {
        redisTemplate.opsForHash().put(key, field, value);
        return Result.ok(null);
    }

    @GetMapping("/hash")
    public Result<Map<Object, Object>> getHash(@RequestParam String key) {
        return Result.ok(redisTemplate.opsForHash().entries(key));
    }

}
