package my.demo.redistest;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisTest {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	
	public void test(){
		stringRedisTemplate.opsForValue().set("test", "testv");
		
		stringRedisTemplate.opsForValue().set("KEY", "1", 10, TimeUnit.SECONDS);
	}
	
	public String test2(){
		return stringRedisTemplate.opsForValue().get("test");
	}
}
