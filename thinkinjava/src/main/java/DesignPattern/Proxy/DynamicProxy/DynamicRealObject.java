package DesignPattern.Proxy.DynamicProxy;

/**
 * Created by wangbo on 2018/3/13.
 */
public class DynamicRealObject implements DynamicInterface {

    @Override
    public void doSomeThing() {
        System.out.println("doSomeThing");
    }

    @Override
    public void someThingElse() {
        System.out.println("someThingElse");
    }
}
