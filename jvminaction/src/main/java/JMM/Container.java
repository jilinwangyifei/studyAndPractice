package JMM;

import sun.misc.Unsafe;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangbo on 2018/7/23.
 *
 * 代码参考 https://tech.meituan.com/java_memory_reordering.html
 */
public class Container {

    public static class SomeThing{

        private int status;

        public SomeThing(){
            status = 1;
        }

        public int getStatus() {
            return status;
        }
    }

   private SomeThing object;

    private Object value;

    private static final Unsafe unsafe = getUnsafe();

    private static  long valueOffset = 0l;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset(Container.class.getDeclaredField("value"));
        }catch (Exception e) {

        }
    }

    public void create () {
        SomeThing tmp = new SomeThing();
        //将value设置为null只是一项无用操作 实际上用到的是他得内存屏障
        unsafe.putOrderedObject(this,valueOffset,null);
        object = tmp;
    }

    /*private volatile  SomeThing object;

    public void create() {
        object = new SomeThing();
    }
*/

    public SomeThing get() {
        while (object == null) {
            Thread.yield();
        }
        return object;
    }


    public static Unsafe getUnsafe () {
        try {
            //由于直接调用Unsafe.getUnsafe()需要配置JRE获取较高权限，
            //我们利用反射获取Unsafe中的theUnsafe来取得Unsafe的可用实例。
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        }catch (Exception e) {

        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        final int THREADS_COUNT = 20;
        final int LOOP_COUNT = 100000;

        long sum = 0;
        long min = Integer.MAX_VALUE;
        long max = 0;
        for(int n = 0;n <= 100;n++) {
            final Container basket = new Container();
            List<Thread> putThreads = new ArrayList<Thread>();
            List<Thread> takeThreads = new ArrayList<Thread>();
            for (int i = 0; i < THREADS_COUNT; i++) {
                putThreads.add(new Thread() {
                    @Override
                    public void run() {
                        for (int j = 0; j < LOOP_COUNT; j++) {
                            basket.create();
                        }
                    }
                });
                takeThreads.add(new Thread() {
                    @Override
                    public void run() {
                        for (int j = 0; j < LOOP_COUNT; j++) {
                            basket.get().getStatus();
                        }
                    }
                });
            }
            long start = System.nanoTime();
            for (int i = 0; i < THREADS_COUNT; i++) {
                takeThreads.get(i).start();
                putThreads.get(i).start();
            }
            for (int i = 0; i < THREADS_COUNT; i++) {
                takeThreads.get(i).join();
                putThreads.get(i).join();
            }
            long end = System.nanoTime();
            long period = end - start;
            if(n == 0) {
                continue;    //由于JIT的编译，第一次执行需要更多时间，将此时间不计入统计
            }
            sum += (period);
            System.out.println(period);
            if(period < min) {
                min = period;
            }
            if(period > max) {
                max = period;
            }
        }
        System.out.println("Average : " + sum / 100);
        System.out.println("Max : " + max);
        System.out.println("Min : " + min);

    /*
        volatile下运行时间
        Average : 39117515
        Max : 49824262
        Min : 35482358

        unsafe下运行时间
        Average : 35480524
        Max : 42488735
        Min : 31658569
        总结:
        即使在其它会发生写写重排序的处理器中，由于StoreStore屏障的性能损耗小于StoreLoad屏障，
        采用这一方法也是一种可行的方案。但值得再次注意的是，这一方案不是对volatile语义的等价替换，
        而是在特定场景下做的特殊优化，它仅避免了写写重排序，但不保证内存可见性。
    */
    }

}
