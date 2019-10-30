package GC;

import java.net.URLDecoder;

/**
 * Created by wangbo on 2018/3/20.
 */
public class GarbageCollector {
    /**
     * 垃圾收集器
     *
     * 1.串行收集器
     *      特点一 使用单线程进行垃圾回收
     *      特点二 独占式的垃圾回收
     *      配置 -XX:+UseSerialGc 新生代使用复制算法 老年代使用标记压缩算法
     * 2.并行收集器
     *      新生代ParNew回收器
     *          复制算法
     *          相同是 和串行收集器一样(回收策略 算法 参数配置)
     *          不同是 收集过程多线程化 单CPU效果并不好 可以使用ParallelGcThreads配置回收时工作线程数量
     *      新生代ParallelGc回收器
     *          复制算法
     *          特点 非常关注系统的吞吐量
     *          -XX:MaxGcPauseMillis 设置最大垃圾收集停顿时间
     *          -XX:GcTimeRatio 设置吞吐量大小
     *          注意:以上两者互相矛盾 增大吞吐量会导致垃圾收集停顿时间更长 反之亦然
     *          可以使用-XX:UseAdaptiveSizePolicy打开自适应GC策略
     *      老年代ParallelOldGc回收器
     *          和ParallelGc回收器一样 作用在老年代 和ParallelGc搭配使用
     * 3.CMS收集器(CMS concurrent mark sweep 并发标记清除)
     *      特点 关注吞吐量
     *      算法 标记清除算法
     *      过程 初始标记(独占)--->并发标记(并发)--->预清理(并发)--->重新标记(独占)--->并发清理(并发)--->并发重置(并发)
     *           预处理时 会刻意等待新生代gc的发生 预测下一次gc发生时间 之后在当前和预测时间之间进行重新标记
     *                   避免重新标记和新生代gc同时发生 减少停顿时间
     *       -XX:+UseConcMarkSweepGc 启用CMS收集器 -XX:ConcGcThreads -XX:ParallelCMSThreads 并发线程数量
     *       -XX:CMSInitiatingOccupancyFraction 默认是68 当老年代使用率68%时 执行CMS回收
     *           (内存使用率增长很快时 CMS回收会失败 会启动老年代串行收集器进行垃圾回收 程序将中断 停顿时间很长)
     *           (调优 内存增长缓慢 阈值设置较大 减少回收次数 增长快速 降低阈值 避免频繁触发串行回收)
     *       -XX:+UseCMSCompactAtFullCollection 在垃圾回收完成后 进行一次碎片整理
     *       -XX:CMSFGcsBeforeCompaction 设置在多少次CMS后 进行一次垃圾回收
     *       -XX:+CMSClassUnloadingEnabled 回收Perm区
     *       -XX:+CMSScavengeBeforeRemark 在remark之前强制一次Minor Gc remark过程是独占的
     *            在此之前触发minor gc可以减少remark时间 从而优化整体时间(触发minor gc主要由于跨代引用 remark会扫描全代)
     * 4.G1收集器
     *   特点
     *       并行性
     *          gc回收期间 多个gc线程可以同时工作
     *       并发性
     *          G1拥有与应用程序交替执行的能力 部分工作可以与应用程序同时执行
     *       分代GC
     *          同时兼顾新生代和老年代 和之前的回收器很大的不同
     *       空间整理
     *          每次回收都会有效的复制对象 减少空间碎片
     *       可预见性
     *          由于分区的原因 可以只选取部分区域进行内存回收 缩小了回收的范围
     *   4个阶段
     *      新生代gc
     *
     *      并发标记周期
     *          初始标记(独占)
     *          根区域扫描
     *          并发标记
     *          重新标记(独占)
     *          独占清理(独占)
     *          并发清理阶段
     *
     *      混合收集
     *          既会执行正常的年轻代GC 又会选取一些被标记的老年代区域进行回收
     *          它同时处理了新生代和老年代
     *      如果需要 可能会full gc
     *   配置
     *      -XX:+UseG1Gc 设置G1回收器
     *      -XX:MaxPauseMillis 目标最大停顿时间
     *          (如果停顿时间缩短 对新生代来说 会增加新生代GC的次数 GC反而更加频繁
     *           对于老年代来说 为了获取更短的停顿时间 那么在混合GC收集时
     *           一次收集的区域数量变少 增加了Full Gc可能性)
     *      -XX:ParallelGcThreads并行回收时 GC工作线程数量
     *      -XX:InitiatingHeapOccupancyPercent
     *          堆使用率达到多少 触发并发周期的执行 默认值45 值设置偏大 会导致并发周期迟迟得不到启动
     *          那么引起Full GC 可能性也会大大增大 反之 过小的值 会使得并发周期非常频繁
     *          大量GC线程抢占CPU 导致应用程序性能有所下降
     *
     *
         * YGC的时机:
     *
         * edn空间不足
     *
         * FGC的时机：
     *
         * 1.old空间不足；
     *
         * 2.perm空间不足；
     *
         * 3.显示调用System.gc() ，包括RMI等的定时触发;
     *
         * 4.YGC时的悲观策略；
     *
         * 5.dump live的内存信息时(jmap –dump:live)。
     *
     *
     *
     */


}
