package my.demo.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.demo.aop.TestAnn;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TestInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("pre:"+handler.getClass());
		if(handler instanceof HandlerMethod){
			HandlerMethod hm=(HandlerMethod)handler;
			System.out.println(hm.getBeanType());
			System.out.println(hm.getMethod());
			System.out.println(hm.getMethodAnnotation(TestAnn.class));
		} 
		
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle:"+handler.getClass());
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion:"+handler.getClass());

	}

}
