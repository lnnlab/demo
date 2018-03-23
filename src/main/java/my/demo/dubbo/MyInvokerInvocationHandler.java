package my.demo.dubbo;

import java.lang.reflect.Method;
import java.util.Objects;

import my.demo.aop.TestAnn;

import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.proxy.InvokerInvocationHandler;

public class MyInvokerInvocationHandler extends InvokerInvocationHandler {

	private Object target;
	private static final int ZERO = 0;

	public <T> MyInvokerInvocationHandler(T target, Invoker<T> invoker) {
		super(invoker);
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

		final TestAnn ann = method.getAnnotation(TestAnn.class);
		final Class<?>[] arguments = method.getParameterTypes();
		final Class clazz = method.getDeclaringClass();

		if (Objects.nonNull(ann)) {
			try {
				System.out.println(Thread.currentThread().getName());
				System.out.println(ann.value());
				return super.invoke(target, method, args);
			} catch (Throwable throwable) {
				throwable.printStackTrace();
				return getDefaultValue(method.getReturnType());
			}

		} else {
			return super.invoke(target, method, args);
		}

	}

	private Object getDefaultValue(Class type) {
		if (boolean.class.equals(type)) {
			return Boolean.FALSE;
		} else if (byte.class.equals(type)) {
			return ZERO;
		} else if (short.class.equals(type)) {
			return ZERO;
		} else if (int.class.equals(type)) {
			return ZERO;
		} else if (long.class.equals(type)) {
			return ZERO;
		} else if (float.class.equals(type)) {
			return ZERO;
		} else if (double.class.equals(type)) {
			return ZERO;
		}
		return null;
	}

}
