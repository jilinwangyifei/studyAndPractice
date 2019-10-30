package DesignPattern.Singleton;

/**
 * Created by wangbo on 2018/3/3.
 */

/**存在的问题：
 * 没有volatile修饰符，可能出现Java中的另一个线程看到个初始化了一半的_instance的情况，
 * instance创建过程
 * 1.为对象分配内存
 * 2.执行构造方法语句，初始化实例对象
 * 3.把instance的引用指向分配的内存空间
 * 没有volatile修饰的情况下执行顺序可能为 1->3->2
 * 有修饰的情况下 执行的顺序为1->2->3
 *
 * 但使用了volatile变量后，就能保证先行发生关系（happens-before relationship）。
 * 对于volatile变量_instance，所有的写（write）都将先行发生于读（read），
 * 在Java 5之前不是这样，所以在这之前使用双重检查锁有问题
 */
public class SychroSingle {

    private  SychroSingle(){
    }

    private volatile static SychroSingle instance = null;

    public static SychroSingle getInstace(){
        if(instance == null){
            synchronized(SychroSingle.class) {
                if (instance == null) {
                    instance = new SychroSingle();
                }
            }
        }
        return instance;
    }

}
