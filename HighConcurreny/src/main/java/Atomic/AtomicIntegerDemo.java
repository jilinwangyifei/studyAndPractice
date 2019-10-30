package Atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangbo on 2018/3/8.
 */
public class AtomicIntegerDemo {

    static AtomicInteger i = new AtomicInteger();

    static class AddThread implements Runnable{

        @Override
        public void run() {
            for (int j = 0; j < 1000; j++) {
                i.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];

        for (int j = 0; j < 10 ; j++) {
            threads[j] = new Thread(new AddThread());
        }

        for (int j = 0; j < 10; j++) {
            threads[j].start();
        }
        for (int j = 0; j < 10; j++) {
            threads[j].join();
        }
        System.out.println(i);
    }
}
