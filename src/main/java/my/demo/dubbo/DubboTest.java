package my.demo.dubbo;

import org.springframework.stereotype.Component;


import com.alibaba.dubbo.config.annotation.Service;

@Service(interfaceClass = IDubboTest.class)
@Component
public class DubboTest implements IDubboTest {

	@Override
	public String test() {
		return "dubbo";
	}

}
