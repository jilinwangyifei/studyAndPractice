package DesignPattern.Proxy.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wangbo on 2018/3/13.
 */
public class DynamicProxyHandler implements InvocationHandler {


    private Object proxyed;

    public  DynamicProxyHandler(Object proxyed){
        this.proxyed = proxyed;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在代理真实对象前我们可以添加一些自己的操作
        System.out.println("在调用之前，我要干点啥呢？");

        System.out.println("Method:" + method);

        //当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        Object returnValue = method.invoke(proxyed, args);

        //在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("在调用之后，我要干点啥呢？");

        return returnValue;

    }

    public static void main(String[] args) throws Exception{

        DynamicRealObject dynamicRealObject = new DynamicRealObject();
        InvocationHandler handler = new DynamicProxyHandler(dynamicRealObject);
        ClassLoader loader = dynamicRealObject.getClass().getClassLoader();
        Class[] interfaces = dynamicRealObject.getClass().getInterfaces();

        /**
         * 该方法用于为指定类装载器、一组接口及调用处理器生成动态代理类实例
         */
        DynamicInterface proxy = (DynamicInterface) Proxy.newProxyInstance(loader, interfaces, handler);

        proxy.doSomeThing();
        //proxy.someThingElse();

        /*
        Class ss = DynamicRealObject.class;
        Class<?>[] constructorParams =
                { InvocationHandler.class };
        Constructor constructor=  ss.getConstructor(constructorParams);

        DynamicInterface dynamicInterface= (DynamicInterface) constructor.newInstance(new Object[] {handler});
        dynamicInterface.doSomeThing();*/
    }
}
