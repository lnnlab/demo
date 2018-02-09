package my.demo;


import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired RedisTemplate<String, Object>  redisTemplate;
    
	@Test
	public void contextLoads() {
	}
	@Test
	public void testRedis(){
		redisTemplate.opsForValue().set("test-spring", "test");
		
		 String v= (String) redisTemplate.opsForValue().get("test-spring");
		 
		 Assert.assertEquals("test", v);
	}
	
	@Test
	public void testWebService(){
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8080/sboot-dev/services/itest?wsdl");
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,
        // PASS_WORD));
        Object[] objects = new Object[0];
        try {
           
            objects = client.invoke("test", "hello webservice");
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
	}

}
