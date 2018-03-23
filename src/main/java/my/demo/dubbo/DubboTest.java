package my.demo.dubbo;

import my.demo.aop.TestAnn;

import com.alibaba.dubbo.config.annotation.Service;

@Service(interfaceClass = IDubboTest.class,filter="mytest")
public class DubboTest implements IDubboTest {

	@Override
	@TestAnn("test-ann")
	public String test() {
		return "dubbo";
	}

}
