package ParallelBasic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangbo on 2018/3/10.
 */
public class CountDownLatchDemo implements Runnable {

    static CountDownLatch cd = new CountDownLatch(10);

    @Override
    public void run() {
        try{
            Thread.sleep(1000);
            System.out.println("check complete");
            cd.countDown();

        }catch (Exception e){

        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10 ; i++) {
            executorService.submit(new CountDownLatchDemo());
        }
        //等待检查
        cd.await();
        //发射火箭
        System.out.println("Fire!!!");
        executorService.shutdown();
    }
}
