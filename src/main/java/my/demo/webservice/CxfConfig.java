package my.demo.webservice;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class CxfConfig {
	@Autowired
	private Bus bus;
	@Autowired
	private ITest iTest;

	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, iTest);
		endpoint.publish("/itest");// 接口发布在 /itest 目录下
		return endpoint;
	}
}