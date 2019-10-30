package Atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by wangbo on 2018/3/8.
 */

/**
 * 注意 1 updater 只能修改可见范围内的变量 不可将变量声明称private,Updater使用反射得到这个变量，如果变量不可见，则会报错
 *     2 为了确保变量被正确的读取 必须是volatile
 *     3 cas操作通过对象实例中的偏移量直接赋值 它不支持static字段(静态变量)
 */
public class AtomicIntegerFieldUpdaterDemo {

    public static class Candidate{
        int id;
        volatile int score;
    }

    public static final AtomicIntegerFieldUpdater<Candidate> scoreUpater = AtomicIntegerFieldUpdater.newUpdater(Candidate.class,"score");

    public static AtomicInteger allScore = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException{
        final Candidate candidate = new Candidate();
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(){
                @Override
                public void run() {
                    if(Math.random() > 0.4){
                        scoreUpater.incrementAndGet(candidate);
                        allScore.incrementAndGet();
                    }
                }
            };
            threads[i].start();
        }
        for (int i = 0; i < 1000; i++) {
            threads[i].join();
        }
        System.out.println("candidate score"+candidate.score);
        System.out.println("       allScore"+allScore.get());

    }

}
