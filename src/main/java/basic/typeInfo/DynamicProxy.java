package basic.typeInfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements DynamicProxyInterface {

    @Override
    public void f() {
        System.out.println("DynamicProxy.f()");
    }

    public static void main(String[] args) {
        DynamicProxyInterface dynamicProxy = new DynamicProxy();
        DynamicProxyInterface proxy = (DynamicProxyInterface)Proxy.newProxyInstance(
                DynamicProxy.class.getClassLoader(),
                new Class[]{DynamicProxyInterface.class},
                new DynamicProxyHandler(dynamicProxy)
        );
        proxy.f();
    }

}

interface DynamicProxyInterface {
    void f();
}

class DynamicProxyHandler implements InvocationHandler {

    private Object proxied;

    DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoking proxied method ");
        Object object = method.invoke(proxied, args);
        System.out.println("after invoking proxied method ");
        return object;
    }
}

