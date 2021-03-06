package Memory;

/**
 * Created by wangbo on 2018/3/25.
 */
public class Heap {

    /**
     *堆
     *   新生代
     *      eden
     *      s0
     *      s1
     *   老年代
     *   参考 http://bigsec.com/bigsec-news/anan-201784-JVM
     * jvm为什么要分代
     *     堆内存是虚拟机管理的内存中最大的一块，也是垃圾回收最频繁的一块区域，我们程序所有的对象实例都存放在堆内存中。
     *     给堆内存分代是为了提高对象内存分配和垃圾回收的效率。试想一下，如果堆内存没有区域划分，
     *     所有的新创建的对象和生命周期很长的对象放在一起，随着程序的执行，堆内存需要频繁进行垃圾收集，而每次回收都要遍历所有的对象，
     *     遍历这些对象所花费的时间代价是巨大的，会严重影响我们的GC效率，这简直太可怕了。
     *     有了内存分代，情况就不同了，新创建的对象会在新生代中分配内存，经过多次回收仍然存活下来的对象存放在老年代中，
     *     静态属性、类信息等存放在永久代中，新生代中的对象存活时间短，只需要在新生代区域中频繁进行GC，老年代中对象生命周期长，
     *     内存回收的频率相对较低，不需要频繁进行回收，永久代中回收效果太差，一般不进行垃圾回收，还可以根据不同年代的特点采用合适的垃圾收集算法。
     *     分代收集大大提升了收集效率，这些都是内存分代带来的好处。
     *
     */

}
