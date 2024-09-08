package com.hotel.userservice.utils;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Aspect
@Component
@Order(1)
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CacheAspect {

    private static Logger logger = (Logger) LoggerFactory.getLogger(CacheAspect.class);
    private CacheManager cacheManager;
    private StringRedisTemplate redisTemplate;

    private Class<?>[] getMethodParameterTypes(ProceedingJoinPoint joinPoint) {
        return Arrays.stream(joinPoint.getArgs())
                .map(Object::getClass)
                .toArray(Class<?>[]::new);
    }

    private CustomCache getAnnotation(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        return joinPoint.getTarget().getClass()
                .getDeclaredMethod(joinPoint.getSignature().getName(), getMethodParameterTypes(joinPoint))
                .getAnnotation(CustomCache.class);
    }

    private Long getCacheDuration(String cacheKey, int ttl2) {
        return ttl2 - getTTL(cacheKey);
    }

    private Long getTTL(String cacheKey) {
        Long ttl = redisTemplate.getExpire(cacheKey, TimeUnit.SECONDS);
        if(ttl == null || ttl == -2 || ttl == -1) {
            return 0L;
        }
        return ttl;
    }

    @Around("@annotation(CustomCache)")
    public Object cacheMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        CustomCache annotation = getAnnotation(joinPoint);

        if (annotation == null) {
            return joinPoint.proceed(); // Not a cached method
        }

        Cache cache = cacheManager.getCache(annotation.cacheName());
        if(cache == null) {
            logger.error("Cache not found: " + annotation.cacheName());
            return joinPoint.proceed(); // Cache not found
        }   

        // 1. Check Cache for Fresh Data (considering TTL1)
        ValueWrapper cachedEntry = cache.get(annotation.key());

        if (cachedEntry != null) {
          
            Long cachePutDuration = getCacheDuration("user-service:" + annotation.cacheName() + "::" + annotation.key(), annotation.ttl2()); 
            if (cachePutDuration < annotation.ttl1()) {
                // Fresh data within TTL1 window
                return cachedEntry.get();
            }
        }

        // 2. Cache Miss or Stale Data (outside TTL1 window)
        Object result = joinPoint.proceed(); // Execute the original method

        // 3. Update Cache with Fresh Data (full TTL2)
       cache.put(annotation.key(), result);

        return result;
    }
}
