package LockAndConcurrent;

/**
 * Created by wangbo on 2018/3/19.
 *
 * 原子性
 *
 * 可见性
 *
 * 有序性
 *
 * HAPPEN BEFORE 原则
 *
 */
public class JMMTest extends Thread{

    //验证可见性
    //1.使用volatile
    //2.synchronized
    private volatile boolean stop = false;

    public synchronized void stopMe(){
        stop = true;
    }

    @Override
    public void run() {
        int i = 0;
        while (!stop){
           i ++;
        }
        System.out.println("Thread stop");
    }

    public static void main(String[] args) throws InterruptedException{
        JMMTest t = new JMMTest();
        t.start();
        Thread.sleep(1000);
        t.stopMe();
        Thread.sleep(1000);
    }
}
