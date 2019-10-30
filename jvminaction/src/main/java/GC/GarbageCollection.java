package GC;

/**
 * Created by wangbo on 2018/3/20.
 */
public class GarbageCollection {
    /**
     * 垃圾回收算法
     *
     * 判断对象是否可以回收
     * 1.引用计数法
     *     问题:
     *     1)有循环引用问题 A中有B B中有A
     *     2)引用产生和消除时 会有加法和减法操作 对系统性能有影响
     *
     *     // 在栈中分配内存空间给obj1，然后在堆中创建GcObject对象A
     *     // 将obj1指向A实例，这时A的引用计数值 = 1
     *     GcObject obj1 = new GcObject();
     *     // 同理，GcObject实例B的引用计数值 = 1
     *     GcObject obj2 = new GcObject();
     *     // GcObject实例2被引用，所以B引用计数值 = 2
     *     obj1.instance = obj2;
     *     // 同理A的引用计数值 = 2
     *     obj2.instance = obj1;
     *     // 栈中的obj1不再指向堆中A，这时A的计数值减1，变成1
     *     obj1 = null;
     *     // 栈中的obj2不再指向堆中B，这时B的计数值减1，变成1
     *     obj2 = null;
     *
     *     class GcObject {
     *          public Object instance = null;
     *     }
     *     参考图片 引用计数法的弊端
     *     摘自 https://www.jianshu.com/p/d9840ebdea25
     *
     * 2.可达性分析算法
     *     以"GC Roots"的对象作为起始点向下搜索 形成引用链
     *     当一个对象没有和引用链相连时 则证明此对象是不可用的
     *     可以作为gc roots根节点的对象
     *     1）虚拟机栈中引用的对象
     *     2）方法区中类静态属性引用的对象
     *     3）方法区中常量引用的对象
     *     4）本地方法栈中JNI(native方法)引用的对象
     *     摘自 深入理解jvm P64
     *
     * 垃圾回收算法
     * 1.标记清除法
     *
     * 2.复制算法
     *
     * 3.标记压缩法
     *
     * 4.分代算法
     *
     * 5.分区算法
     *
     */
}
