package Memory;

/**
 * Created by wangbo on 2018/3/21.
 */
public class PermSpace {
    /**
     *方法区:两块主要的子区域
     *
     *1.持久代  这个区域会存储包括类定义，结构，字段，方法（数据及代码）以及常量在内的类相关数据。
     *   它可以通过-XX:PermSize及 -XX:MaxPermSize来进行调节。如果它的空间用完了，
     *   会导致java.lang.OutOfMemoryError: PermGen space的异常。
     *2.代码缓存  这个缓存区域是用来存储编译后的代码。编译后的代码就是本地代码（硬件相关的），
     *   它是由JIT（Just In Time)编译器生成的，这个编译器是Oracle HotSpot JVM所特有的。
     *3.字符串常量池
     *      在 jdk1.6（含）之前也是方法区的一部分，并且其中存放的是字符串的实例；
     *      在 jdk1.7（含）之后是在堆内存之中，存储的是字符串对象的引用，字符串实例是在堆中；
     *4.运行时常量池
     *    字面量
     *    符号引用
     *为什么移除持久代?
     *   它的大小是在启动时固定好的——很难进行调优。-XX:MaxPermSize，设置成多少好呢？
     *   简化Full GC：每一个回收器有专门的元数据迭代器。
     *   可以在GC不进行暂停的情况下并发地释放类数据。
     *   使得原来受限于持久代的一些改进未来有可能实现
     *
     * 元空间的特点：
     *   充分利用了Java语言规范中的好处：类及相关的元数据的生命周期与类加载器的一致。
     *   每个加载器有专门的存储空间
     *   只进行线性分配
     *   不会单独回收某个类
     *   省掉了GC扫描及压缩的时间
     *   元空间里的对象的位置是固定的
     *   如果GC发现某个类加载器不再存活了，会把相关的空间整个回收掉
     *   元空间的内存分配模
     *
     */
}
