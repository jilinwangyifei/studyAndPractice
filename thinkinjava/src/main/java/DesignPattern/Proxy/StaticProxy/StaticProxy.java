package DesignPattern.Proxy.StaticProxy;

/**
 * Created by wangbo on 2018/3/31.
 */
public class StaticProxy {

    StaticService staticService ;

    StaticProxy(StaticService staticService){
        this.staticService = staticService ;
    }

    public void doSomeThing() {
        System.out.println("before doSomething");
        staticService.doSomeThing();
        System.out.println("after doSomething");
    }

}
