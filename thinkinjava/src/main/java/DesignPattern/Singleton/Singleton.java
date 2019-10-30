package DesignPattern.Singleton;

/**
 * Created by wangbo on 2018/3/3.
 */
/*
  注意 1 single设置为私有的
      2 instance必须是 private static
  缺点 如果在其他地方有调用Single.sattus 那么实例会提前创建而不是在调用getInstance的时候
 */
public class Singleton {

    public static int status;

    private  Singleton(){
        System.out.print("单例创建");
    }
    private static Singleton instance = new Singleton();

    public static Singleton getInstance(){
        return instance;
    }
}
