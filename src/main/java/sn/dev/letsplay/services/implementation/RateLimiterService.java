package sn.dev.letsplay.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RateLimiterService {
    private final StringRedisTemplate stringRedisTemplate;

    public boolean isAllowed(String username) {
        String key = "RATELIMIT_:" + username;
        Long current = stringRedisTemplate.opsForValue().increment(key);
        if (current == 1) {
            stringRedisTemplate.expire(key, Duration.ofSeconds(60));
        }
        return current <=  3;
    }
}
