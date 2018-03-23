package my.demo.dubbo;

import com.alibaba.dubbo.rpc.proxy.jdk.JdkProxyFactory;
import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.proxy.InvokerInvocationHandler;

import java.lang.reflect.Proxy;
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
