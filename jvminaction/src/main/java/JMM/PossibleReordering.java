package JMM;

/**
 * Created by wangbo on 2018/7/19.
 */
//指令重排序

//As-if-serial语义的意思是，所有的动作(Action)5都可以为了优化而被重排序
//                        但是必须保证它们排序后的结果和程序代码本身的应有结果是一致的
//先行发生原则 happen-before
/* 1.程序次序原则 线程中的每个动作A都happens-before于该线程中的每一个动作B 其中 在程序中
                 所有的动作B都能出现在A之后。
 * 2.监视器法则  对监视器锁的解锁happen before于每一个后续对监视器的加锁动作
 * 3.volatile法则 对volatile域的写操作happen before每一个后续对同一个域的读写操作
 * 4.线程启动法则 在一个线程里 对thread.start的调用会happen before于每个启动线程的动作
 * 5.线程终结法则 线程中的任何动作 happen before其他线程检测到这个线程已经终结
 *              或者从thread.join调用中成功返回 或thread.isActive返回false
 * 6.中断法则 一个线程调用另一个线程的interrupt happen before 于被中断的线程发现中断
 * 7.终结法则 一个对象构造函数的结束happen before 这个对象finalize的开始
 * 8.传递性 a happen before b  b happen befor c  a happe befor c
 *
 * final 对final语义的扩展保证一个对象的构建方法结束前，所有final成员变量都必须完成初始化
 */
public class PossibleReordering {

    static int x = 0, y = 0;
    static int a = 0, b = 0;

    public static void main(String[] args) throws  InterruptedException{
        Thread one = new Thread(new Runnable() {
            public void run() {
                a = 1;
                x = b;
            }
        });

        Thread two = new Thread(new Runnable() {
            public void run() {
                b = 1;
                y = a;
            }
        });

        one.start();
        two.start();

        one.join();
        two.join();

        System.out.println("(" + x + "," + y + ")");
        //可能输出的结果 (1,0)、(0,1)、(1,1)、(0,0)
    }

}
