package ParallelBasic;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by wangbo on 2018/3/10.
 */
public class BadSuspend {
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
                Thread.currentThread().suspend();
                System.out.println(getName()+"执行完毕");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        c1.start();
        c1.resume();
        Thread.sleep(100);
        c2.start();
        c2.resume();
        c1.join();
        c2.join();

    }
}
