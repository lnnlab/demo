package com.lnnlab.demo.redistest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisTest {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	
	public void test(){
		stringRedisTemplate.opsForValue().set("test", "testv");
	}
	
	public String test2(){
		return stringRedisTemplate.opsForValue().get("test");
	}
}
