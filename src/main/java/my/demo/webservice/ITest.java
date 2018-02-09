package my.demo.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import my.demo.web.AjaxMsg;

@WebService
public interface ITest {
	
	@WebMethod
	public AjaxMsg test(@WebParam(name="uu") String test);
}
