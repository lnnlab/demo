package com.lnnlab.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lnnlab.demo.redistest.RedisTest;

@RestController
public class TestController {
	@Autowired
	private RedisTest test;
	
	@Value("${test.config}")
	private String config;

	@RequestMapping("/test")
	public String test(HttpServletRequest requset) {
		
		requset.getSession().setAttribute("test", "test");
		test.test();
		return test.test2()+"-"+config;
	}

}
