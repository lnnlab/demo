package my.demo.controller;

import javax.servlet.http.HttpServletRequest;

import my.demo.dubbo.IDubboTest;
import my.demo.redistest.RedisTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.config.annotation.Reference;

@Controller
public class TestController {
	@Autowired
	private RedisTest test;

	@Value("${test.config}")
	private String config;

	@RequestMapping("/test")
	public String test(HttpServletRequest requset) {

		requset.getSession().setAttribute("test", "test");
		test.test();
		return test.test2() + "-" + config;
	}
	
	@RequestMapping("/testv")
	public String testv(HttpServletRequest requset) {
		return "testv";
	}

	@RequestMapping("/test2")
	public String test2(HttpServletRequest requset) {
		throw new RuntimeException("test");
	}

	@RequestMapping("/test3")
	public String test3(HttpServletRequest requset) {
		return "500";
	}

	@RequestMapping("/testdubbo")
	public String testdubbo(HttpServletRequest requset) {
		return "test";
	}


}
