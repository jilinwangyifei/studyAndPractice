package ParallelBasic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangbo on 2018/3/8.
 */
public class ThreadLocalDemo_gc {

    static volatile ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected void finalize() throws Throwable {
            System.out.println(this.toString()+"is gc");
        }
    };

    static volatile CountDownLatch countDownLatch = new CountDownLatch(1000);

    static class ParseDate implements Runnable{

        int i = 0;
        public ParseDate(int i){
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if(t1.get() == null){
                    t1.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"){
                        @Override
                        protected void finalize() throws Throwable {
                            System.out.println(this.toString()+"is gc");
                        }
                    });
                    System.out.println(Thread.currentThread().getId()+"simpleDateFormat is created");
                }
                Date date = t1.get().parse("2015-10-12 11:11:"+i%60);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                countDownLatch.countDown();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new ParseDate(i));
        }
        countDownLatch.await();
        System.out.println("mission complete!");
        t1 = null;
        System.gc();
        System.out.println("first gc complete!");

        //在设置threadLocal的时候 会清除threadLocalMap中的无效数据
        t1 = new ThreadLocal<SimpleDateFormat>();
        countDownLatch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new ParseDate(i));
        }
        countDownLatch.await();
        Thread.sleep(1000 );
        System.gc();
        System.out.println("second gc complete!");

    }

}
