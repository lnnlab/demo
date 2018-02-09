package my.demo.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e)
			throws Exception {

		String requestType = req.getHeader("X-Requested-With");
		if ("XMLHttpRequest".equals(requestType)) {
			System.out.println("AJAX请求..");
			return null;
		} else {

			ModelAndView mav = new ModelAndView();
			mav.addObject("exception", e);
			mav.addObject("url", req.getRequestURL());
			mav.setViewName("500");
			return mav;
		}
	}

}