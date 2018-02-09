package my.demo.webservice;

import javax.jws.WebService;

import my.demo.web.AjaxMsg;
import net.minidev.json.JSONArray;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@WebService(serviceName = "TestWebService"// 服务名
, targetNamespace = "http://webservice.demo.my/"// 包名倒叙，并且和接口定义保持一致
, endpointInterface = "my.demo.webservice.ITest")
@Component
public class TestWebService implements ITest {

	@Override
	public AjaxMsg test(String test) {
		return AjaxMsg.ok(test);
	}
	
	
	public static void main(String[] args) {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8080/sboot-dev/services/itest?wsdl");
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,
        // PASS_WORD));
        Object[] objects = new Object[0];
        try {
        	objects = client.invoke("test", "hello webservice");
        	
        	 
        	
            System.out.println("返回数据:" +  JSON.toJSONString(objects[0]));
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
	}

}
