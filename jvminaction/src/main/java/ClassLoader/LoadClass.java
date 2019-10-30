package ClassLoader;

/**
 * Created by wangbo on 2018/3/11.
 */

/**
 * 加载
 *     1 通过类的全名 获取类的二进制数据流
 *     2 解析类的二进制数据流为方法区的数据结构
 *     3 创建java.lang.class类的实例 表示该类型
 * 验证 格式检查 语义检查 字节码验证 符号引用验证
 * 准备 在这个阶段 虚拟机为这个类分配相应的内存空间 并设置初始值(final在准备阶段赋值)
 * 解析 将类 接口 字段和方法的符号引用转为直接引用
 * 初始化 前面步骤没有问题 类可以顺利装载到系统中 此时 类才会开始执行java字节码
 *       初始化阶段的重要工作是执行类的初始化方法<clinit>(编译器自动生成的)
 *
 * 加载class到jvm中的方式 两者区别
 * 1)Class.forName("className")
 *      得到的class是已经初始化完成的 应用加载数据库的驱动
 * 2)ClassLoader.loadClass("className")
 *      得到的class是还没有链接的
 */
public class LoadClass {
    public static void main(String[] args) throws Exception {
        String a = Integer.toString(1)+Integer.toString(2)+Integer.toString(3);
        String b = "123";
        System.out.println(a.equals(b));
        System.out.println(a == b );
        System.out.println(a.intern() == b);
        /**
         * java虚拟机的常量池中 会维护一张字符串拘留表 会保存所有出现过的字符串常量
         * 并且没有重复项 由于a在拘留表中的引用 就是b 因此a.intern()==b 返回true
         */
        Class.forName("ClassLoader.LoadClass");
    }
}
