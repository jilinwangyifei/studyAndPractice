package ClassLoader;

/**
 * Created by wangbo on 2018/3/11.
 */

/**
 * 当创建一个类的实例时，比如使用new 反射 克隆 反序列化
 * 当调用类的静态方法时 使用字节码 invokeStatic
 * 使用类或接口的静态字段时 比如使用 getstatic或者putstatic指令
 * 使用java.lang.reflect包中的方法反射类的方法
 * 初始化子类时，首先初始化父类
 * 作为启动虚拟机，包含main方法的那个类
 */
//使用该类 是不是主动使用
public class WhenInitClass {

    static {
        System.out.println("作为启动虚拟机，包含main方法的那个类");
    }

    static class Parent{
        public Parent(){
            System.out.println("Parent 构造函数");
        }

        static {
            System.out.println("parent init");
        }

    }

    static class Child extends Parent{
        public Child(){
            super();
            System.out.println("Child 构造函数");
        }

        static {
            System.out.println("child init");
        }
    }

    public static void main(String[] args) {
        Child child = new Child();
    }

}

/**
 * 参考博客地址 http://jm.taobao.org/2010/07/21/331/
 *
 *  初始化顺序：
 1，为A类分配内存空间，初始化所有成员变量为默认值，包括primitive类型(int=0,boolean=false,…)和Reference类型。
    首先调用B的静态方法块或静态变量 之后在调用A的静态方法块或静态变量
 2，调用A类构造函数。
 3，调用B类构造函数。
 4，调用Object空构造函数。（java编译器会默认加此构造函数，且object构造函数是个空函数，所以立即返回）
 5，初始化B类成员变量，因为B类没有成员变量，跳过。
 6，执行sysout输出子类A的成员变量小a。// 此时为0
 7，初始化A类成员变量，将A类成员变量小a赋值100。
 8，执行sysout输出当前A类的成员变量小a。// 此时为100
 9，赋值当前A类的成员变量小a为200。
 10，main函数中执行sysout，输出A类实例的成员变量小a。// 此时为200
 *
 *  注意:成员变量初始化是在父类构造函数调用完后，在此之前，成员变量的值均是默认值。
 */


class A extends B {

    public int a = 100;

    public static int aa = 200;

    static {
        System.out.println("A init");
    }

    public A() {
        super();
        System.out.println(a);
        a = 200;
    }

    public static void main(String[] args) {
        System.out.println(new A().a);
    }
}

class B {

    public static int bb = 200;

    public B() {
        System.out.println(((A) this).a);
    }
}
