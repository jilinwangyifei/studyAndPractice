package ClassLoader;

/**
 * Created by wangbo on 2018/3/11.
 */

/**
 *引用final常量并不会引起类的初始化，final变量在准备阶段赋值
 *
 *在此方法中FinalFiledClass没有被加载
 */
public class FinalFiledClass {
    public static final String constString = "CONST";

    static {
        System.out.println("FinalFiledClass init");
    }

    static class UseFiledClass{
        public static void main(String[] args) {
            System.out.println(FinalFiledClass.constString);
        }
    }
}
