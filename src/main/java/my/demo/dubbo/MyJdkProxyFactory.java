package my.demo.dubbo;

import java.lang.reflect.Proxy;

import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.proxy.InvokerInvocationHandler;
import com.alibaba.dubbo.rpc.proxy.jdk.JdkProxyFactory;
public class MyJdkProxyFactory extends JdkProxyFactory{
	@Override
    @SuppressWarnings("unchecked")
    public <T> T getProxy(Invoker<T> invoker, Class<?>[] interfaces) {
        T proxy = (T)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                interfaces, new InvokerInvocationHandler(invoker));
        return  (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                interfaces, new MyInvokerInvocationHandler(proxy,invoker));

    }
}
