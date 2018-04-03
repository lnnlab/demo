package my.demo.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * @author linn
 * 配置拦截器、fliter，servlet，ServletListener 例子
 *
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {
	public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**");
	}
	
	
	
	@Bean
	public FilterRegistrationBean filterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new MyFilter());// 添加过滤器
		registration.addUrlPatterns("/*");// 设置过滤路径，/*所有路径
		registration.addInitParameter("name", "alue");// 添加默认参数
		registration.setName("MyFilter");// 设置优先级
		registration.setOrder(1);// 设置优先级
		return registration;
	}

	@Bean
	public ServletRegistrationBean testServletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new TestServlet());
		registration.addUrlMappings("/hello");
		return registration;
	}

	@Bean
	public ServletListenerRegistrationBean<TestListener> testListenerRegistration() {
		ServletListenerRegistrationBean<TestListener> registration = new ServletListenerRegistrationBean<TestListener>(new TestListener());
		return registration;
	}

	public class TestServlet extends HttpServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			super.doGet(req, resp);
		}

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			resp.getWriter().print("test");
			resp.getWriter().flush();
			resp.getWriter().close();
		}

	}

	public class TestListener implements ServletContextListener {

		@Override
		public void contextDestroyed(ServletContextEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("listener destroy");
		}

		@Override
		public void contextInitialized(ServletContextEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("listener init");
		}

	}

	public class MyFilter implements Filter {
		@Override
		public void destroy() {
		}

		@Override
		public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain) throws IOException, ServletException {
			HttpServletRequest request = (HttpServletRequest) srequest;
			// 打印请求Url
			System.out.println("this is MyFilter,url :" + request.getRequestURI());
			filterChain.doFilter(srequest, sresponse);
		}

		@Override
		public void init(FilterConfig arg0) throws ServletException {
		}
	}
	
}
