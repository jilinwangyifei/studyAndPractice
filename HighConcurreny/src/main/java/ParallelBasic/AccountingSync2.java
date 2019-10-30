package ParallelBasic;

/**
 * Created by wangbo on 2018/3/6.
 */
public class AccountingSync2 implements Runnable {

    static AccountingSync2 accountingSync2 = new AccountingSync2();
    static volatile int i = 0;
    public synchronized void increase(){
        i ++;
    }

    @Override
    public void run() {
        for(int i = 1;i<= 10000; i++){
            increase();
        }
    }

    public static void main(String[] args) throws Exception {
        Thread thread1 = new Thread(accountingSync2);
        Thread thread2 = new Thread(accountingSync2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("i="+i);
    }
}
