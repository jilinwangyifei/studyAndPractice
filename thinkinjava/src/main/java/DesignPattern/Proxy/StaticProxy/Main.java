package DesignPattern.Proxy.StaticProxy;

/**
 * Created by wangbo on 2018/3/31.
 *
 * 参考 https://blog.csdn.net/cslbupt/article/details/70674961
 *
 * 在程序运行之前 静态代理类的.class文件就存在了
 * 动态代理类 在程序运行时 运用反射机制动态生成
 */
public class Main {

    public static void main(String[] args) {
        StaticService staticService = new StaticServiceImpl();
        StaticProxy staticProxy = new StaticProxy(staticService);
        staticProxy.doSomeThing();
    }

}
