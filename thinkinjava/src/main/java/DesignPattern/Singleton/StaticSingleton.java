package DesignPattern.Singleton;

/**
 * Created by wangbo on 2018/3/3.
 */

//终极版 内部类实现
public class StaticSingleton {
    private StaticSingleton(){

    }
    private static class SingletonHolder{
        private static StaticSingleton staticSingleton = new StaticSingleton();
    }

    public static StaticSingleton getInstance(){
        return SingletonHolder.staticSingleton;
    }

    public static void main(String[] args) {
        getInstance();
    }

}
