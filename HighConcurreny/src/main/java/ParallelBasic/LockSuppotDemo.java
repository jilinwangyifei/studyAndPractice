package ParallelBasic;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by wangbo on 2018/3/10.
 */

/**
 * 和 thread.suspend 相比 弥补了由resume在前发生 导致线程无法继续执行的情况
 *
 * unpark操作发生在park之前 它也可以使下一次的park操作立即返回
 *
 * **重要**  为什么unpark发生在park之前 也不会导致线程永久性的挂起？
 *
 *这是因为locksupport采用了类似信号量的机制，它为每个线程准备了一个许可，如果许可可用，那么park函数立即返回，
 * 并且消费这个许可(也就是将可用变为不可用)，如果许可不可用，就会阻塞。而unpark则使一个许可变为可用
 * (和信号量不同的是，许可不能累加，你不可能拥有超过一个许可，它永远只有一个)
 *
 */
public class LockSuppotDemo {

    public static Object o = new Object();

    static ChangeObjectThread c1 = new ChangeObjectThread("t1");
    static ChangeObjectThread c2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread{

        public ChangeObjectThread(String name){
            super.setName(name);
        }
        @Override
        public void run() {
            synchronized (o){
                try {
                    Thread.sleep(3000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("in "+getName());
                LockSupport.park(this);
                System.out.println(getName()+"执行完毕");
            }
         }
    }

    public static void main(String[] args) throws InterruptedException {
        c1.start();
        LockSupport.unpark(c1);
        Thread.sleep(100);
        c2.start();
        LockSupport.unpark(c2);
        c1.join();
        c2.join();

    }
}
