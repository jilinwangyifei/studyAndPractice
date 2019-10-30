package LockAndConcurrent;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by wangbo on 2018/3/17.
 */
public class LongAddrTest {

    static int TaskCount = 3;
    static LongAdder longAdder = new LongAdder();
    static CountDownLatch countDownLatch = new CountDownLatch(TaskCount);

    static class LongAdderThread implements Runnable{
        protected  long startTime;

        public LongAdderThread(long startTime){
            this.startTime = startTime;
        }

        @Override
        public void run() {
            long v = longAdder.sum();
            while (v < 10000){
                longAdder.increment();
                v = longAdder.sum();
            }
            System.out.println("longAdder spend "+(System.currentTimeMillis()-startTime)+"ms");
            countDownLatch.countDown();
        }

    }

    /**
     *为什么会输出10001或者10002
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(50);
        long startTime = System.currentTimeMillis();
        LongAdderThread longAdderThread = new LongAdderThread(startTime);
        for (int i = 0; i < TaskCount  ; i++) {
            pool.submit(longAdderThread);
        }
        countDownLatch.await();
        System.out.println("longAdder="+longAdder.sum());
        pool.shutdown();
    }

}
