package ClassLoader;

/**
 * Created by wangbo on 2018/3/11.
 */

/**
 * 启动类加载器 负责加载系统的核心类 比如 rt.jar中的java类
 * 扩展类加载器 用于加载%java_home%/lib/ext/*.jar
 * 应用类加载器 用于加载用户类，也就是用户程序的类
 * 自定义类加载器
 */
public class PrintClassLoaderTree {

    public static void main(String[] args) {
        ClassLoader cl = PrintClassLoaderTree.class.getClassLoader();
        while(cl != null ){
            System.out.println(cl);
            cl = cl.getParent();
        }
    }
}
