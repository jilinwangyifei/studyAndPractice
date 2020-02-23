package Memory;

/**
 * Created by wangbo on 2018/3/25.
 */
public class Stack {

    /**
     *
     *栈
     *  栈帧
     *      局部变量表
     *          用于保存函数的参数以及局部变量 局部变量表中的变量只在函数中有效
     *          调用结束后 随着栈帧销毁 局部变量表也随之销毁
     *          槽位复用 (局部变量表中的槽位是可以重用的 如果局部变量过了其作用域
     *                  那么在其作用域之申明的新的局部变量可能会复用过期局部变量的槽位)
     *      操作数栈
     *          主要用于保存计算过程的中间结果 计算过程中变量临时的存储空间
     *      帧数据区
     *          保存访问常量池的指针 方便程序访问常量池
     *          异常处理表(发生异常的时候找到处理异常的代码)
     *  配置 -Xss
     *
     *栈上分配
     *  线程私有的对象 可以将它们打散分配在栈上 而不是分配堆上
     *  分配到栈上的好处是可以在函数调用结束后自行销毁 而不用垃圾回收器介入
     *  配置:
     *  -XX:+DoEscapeAnalysis 启用逃逸分析
     *  -XX:+EliminateAllocations 开启标量替换 允许对象分配在栈上
     *
     */


    //参数 -XX:+PrintGC
    static void localVarGc(){
        byte[] b = new byte[6*1024*1024];
        System.gc();
    }

    public static void main(String[] args) {
        localVarGc();
    }

}
