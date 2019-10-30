package LockAndConcurrent;

import java.util.List;
import java.util.Vector;

/**
 * Created by wangbo on 2018/3/17.
 *
 * 偏向锁
 * 开启偏向锁 -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0 -client -Xmx512m -Xms512m
 *
 * 禁用偏向锁 -XX:-UseBiasedLocking -client -Xmx512m -Xms512m
 *
 * 偏向锁在锁竞争激烈的情况下没有太强的优化效果 因为大量的竞争会导致持有锁的线程不停地切换
 * 锁很难保持在偏向模式 此时 使用锁偏向得不到性能的优化 反而会降低系统的性能
 *
 */
public class Biased {
    public static List<Integer> list = new Vector<Integer>();

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        int count = 0;
        int startNumber = 0;
        while (count < 10000000){
            list.add(count);
            startNumber+=2;
            count++;
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时"+(end-begin));
    }
}
