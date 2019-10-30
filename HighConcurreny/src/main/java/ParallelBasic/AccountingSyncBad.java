package ParallelBasic;

/**
 * Created by wangbo on 2018/3/6.
 */
public class AccountingSyncBad implements Runnable {

    static AccountingSyncBad accountingSyncBad = new AccountingSyncBad();
    static volatile int i = 0;

    //对象锁
    /*public synchronized void increase(){
        i ++;
    }*/

    //类的锁
    public static synchronized void increase(){
        i ++;
    }

    @Override
    public void run() {
        for(int i = 1;i< 10000; i++){
            increase();
        }
    }

    public static void main(String[] args) throws Exception {
        Thread thread1 = new Thread(new AccountingSyncBad());
        Thread thread2 = new Thread(new AccountingSyncBad());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("i="+i);
    }
}
