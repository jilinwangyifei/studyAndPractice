package ParallelBasic;

/**
 * Created by wangbo on 2018/9/9.
 */
public class TestSync2 implements Runnable{

    int b = 100;

    synchronized void m1() throws InterruptedException {
       b++;
    }

    synchronized void m2() throws InterruptedException {
       b++;
    }

    @Override
    public void run() {
        try {
            String name = Thread.currentThread().getName();
            m1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // 求证 todo
        // 当2个线程调用2个不同synchronized的方法的时候，认为是没有关系的，这种想法是存在误区的。
        // 直接作用于实例方法:相当于对当前实例加锁，进入同步代码前要获得当前实例的锁。
        TestSync2 testSync2 = new TestSync2();

        Thread t1 = new Thread(testSync2, "testSync1");

        Thread t2 = new Thread(testSync2, "testSync2");

    }

}
