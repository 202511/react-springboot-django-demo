package com.example.demo.ratelimiter;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    public String key() default  "rate_limit:";
    //默认同一个ip 10 秒内， 只能访问10 次
    //主要是登录的时候， 一个人登录的操作10分钟之内只能进行5次
    public int time() default 10;
    public int count() default 10;
}
