package Java8.Chapter9;

public class Test9_4 {

    //9.4.1 解决冲突的三个原则规则
    void test9_4_1() {
        /**
         * 1.类中的优先级最高 高于默认方法
         * 2.第一条无法判断 那么子接口的实现最高 函数签名相同时 优先选择拥有最高实现的默认方法的接口
         * 3.还是无法判断 显示的选择使用一个默认方法的实现
         */
    }

    //9.4.2 选择提供了最具体实现的默认方法的接口
    void test9_4_2() {
        //例子接口 A B C
    }

    //9.4.3 冲突及显式的消除歧义
    void test9_4_3() {
        //例子接口 E F G
    }

    //9.4.4 菱形继承问题
    void test9_4_4() {

    }

}

interface A {
    default void hello() {
        System.out.println("Hello from A");
    }
}

interface B extends A {
    default void hello() {
        System.out.println("Hello from B");
    }
}

class C implements B, A {
    public static void main(String... args) {
        new C().hello();//执行b 根据第二条
    }
}



interface E {
    default void hello() {
        System.out.println("Hello from A");
    }
}

interface F {
   default void hello() {
        System.out.println("Hello from B");
   }
}

class G implements E, F {
    @Override
    public void hello() {
        E.super.hello();
    }
}






