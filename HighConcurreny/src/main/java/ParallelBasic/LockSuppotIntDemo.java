package ParallelBasic;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by wangbo on 2018/3/10.
 */

/**
 *  park()操作不会抛出interruptedException异常 它只会默默的返回
 */
public class LockSuppotIntDemo {

    public static Object o = new Object();

    static ChangeObjectThread c1 = new ChangeObjectThread("t1");
    static ChangeObjectThread c2 = new ChangeObjectThread("t2");
    static ChangeObjectThread c3 = new ChangeObjectThread("t3");

    public static class ChangeObjectThread extends Thread{

        public ChangeObjectThread(String name){
            super.setName(name);
        }

        @Override
        public void run() {
            //synchronized (o){
                System.out.println("in "+getName());
                LockSupport.park(this);
                if(Thread.interrupted()){
                    System.out.println(getName()+"被中断了");
                }
            //}
            System.out.println(getName()+"执行结束");
         }
    }

    public static void main(String[] args) throws InterruptedException {
        c1.start();
        Thread.sleep(100);
        c2.start();
        c1.interrupt();
        c3.start();
        LockSupport.unpark(c2);
        //LockSupport.unpark(c2);
    }
}
