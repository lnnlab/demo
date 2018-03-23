package my.demo.dubbo;

import my.demo.aop.TestAnn;

public interface IDubboTest {
	@TestAnn("test-ann")
	public String test();
}
