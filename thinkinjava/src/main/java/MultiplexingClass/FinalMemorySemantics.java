package MultiplexingClass;

/**
 * Created by wangbo on 2018/11/27.
 *
 *  final内存语义 参考 https://www.jianshu.com/p/d134edd0fad3
 */
public class FinalMemorySemantics {

    //final域的重排序规则

    //读final域的重排序规则

    //写final域的重排序规则

    //final域为引用类型

    //为什么final引用不能从构造函数中"溢出"
    //在引用变量为线程可见之前 该引用变量指向对象的final域已经在构造函数中被初始化过了
    //要达到这个效果还需要一个保证：在构造函数内部 不能让这个被构造对象的引用为其他线程可见
    //也就是对象引用不能在构造函数中 "溢出"

    //对象只有在完全初始化后，其final变量对其它线程才是可见的。这说明，
    // final变量的初始化可以在不使用synchronization的情况下实现线程安全。
    // 这样，final变量实现了真正意义上的final（即，不-可-变），而不会在初始化过程中、后在多线程中看起来会改变。

}
