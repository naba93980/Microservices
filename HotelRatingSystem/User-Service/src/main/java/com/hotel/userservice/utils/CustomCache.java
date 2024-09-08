package com.hotel.userservice.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CustomCache {
    String key();
    String cacheName();
    int ttl1(); // Short-lived TTL
    int ttl2(); // Main TTL in Redis
}

