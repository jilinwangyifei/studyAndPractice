package InnerClass;

/**
 * Created by wangbo on 2018/11/3.
 */
public class AnonymousConstructor {
    public static Base getBase(int i) {
        //在匿名类中不可能有命名构造器(因为他根本就没有名字)
        //通过实例初始化 就能够达到为匿名内部类创建一个构造器的效果
        return new Base(i) {
            {System.out.println("inside instance initialize");}

            @Override
            void f() {
                System.out.println("in anonymous f()");
            }
        };
    }

    public static void main(String[] args) {
        Base b = getBase(10);
        b.f();
    }
}

abstract class Base {
    public Base(int i) {
        System.out.println("base constructor i = "+i);
    }
    abstract void f();

}
