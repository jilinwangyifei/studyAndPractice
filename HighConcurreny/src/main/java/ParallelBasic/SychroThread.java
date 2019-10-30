package ParallelBasic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangbo on 2018/4/2.
 *
 * synchronize 全局锁
 * 1 static synchronized方法
 * 2 synchronized(*.class)
 */
public class SychroThread extends Thread{

    static volatile int i;

    public static synchronized void add(){
            i++;
    }

    @Override
    public void run() {
        SychroThread sychroThread = new SychroThread();
        sychroThread.add();
    }

    public static void main(String[] args) throws Exception {
        SychroThread s1 = new SychroThread();
        SychroThread s2 = new SychroThread();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int j = 0; j < 100 ; j++) {
            executorService.submit(s1);
            executorService.submit(s2);
        }

        Thread.sleep(1000);
        System.out.println(i);
    }

    /* static class Sync{

        static volatile int i;

        public static synchronized void add(){
            i++;
            System.out.println(i);
        }

    }

    @Override
    public void run() {
        Sync sync = new Sync();
        sync.add();
    }

    public static void main(String[] args) {
        for (int j = 0; j < 100 ; j++) {
            SychroThread s2 = new SychroThread();
            s2.start();
        }
    }*/
}
