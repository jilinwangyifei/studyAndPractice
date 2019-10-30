package Compile;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by wangbo on 2018/4/2.
 */
public class JitCompile {

    /**
     * 虚拟机执行方式 解释执行 混合模式 编译执行
     *     解释执行表示代码全部解释执行 不做任何jit编译 -Xint启动
     *     编译执行 无论是否是热点代码都会被编译执行 -Xcomp
     *     混合模式 部分解释 部分编译 取决于是否是热点代码 -Xmixed
     *
     * JIT编译阈值 -XX:CompileThreshold设置阈值 -XX:+PrintCompilation打印编译日志
     *     client模式下 1500
     *     server模式下 1000
     * 多级编译器
     *     客户端编译器 C1 (-client) 编译速度快
     *     服务端编译器 C2 (-server) 编译时间长 更多的编译优化
     * 多级编译策略(在编译速度和执行效率之间取得一个平衡)
     *     -XX:+TieredCompilation 打开多级编译策略 使用该参数 必须使用-server启动 使用-client不会开启
     *     0级 解释执行
     *     1级 简单的C1编译
     *     2级 有限的C1编译
     *     3级 完全C1编译
     *     4级 C2编译
     *
     * 1 OSR栈上替换
     *     不等待函数运行结束 在循环体内将代码替换为编译版本
     *     在每次循环开始时 判断是否有编译版本可供使用
     *     (通过回边计数器和回边指令判断 -回边 就是字节码指令中 向后跳转的指令)
     * 2 方法内联
     *     -XX:+Inline  -XX:-Inline(禁用)
     *     -XX:FreqInlineSize 控制热点方法进行内联的体积上线
     * 设置代码缓存大小
     *     -XX:ReserveCodeCacheSize 缓存区间的大小设置
     *     一旦代码缓存耗尽 JIT就会停止 整个虚拟机的生命周期内 不会再启动
     *
     */

}
