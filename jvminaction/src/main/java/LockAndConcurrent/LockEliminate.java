package LockAndConcurrent;

/**
 * Created by wangbo on 2018/3/17.
 * 锁销除
 *
 * 锁销除  -server -XX:+DoEscapeAnalysis -XX:+EliminateLocks -Xcomp
 *        -XX:-BackgroundCompilation -XX:BiasedLockingStartupDelay=0 -XX:+UseBiasedLocking
 *        耗时 168ms
 *
 *无锁消除 -server -XX:+DoEscapeAnalysis +XX:-EliminateLocks -Xcomp
 *        -XX:-BackgroundCompilation -XX:BiasedLockingStartupDelay=0 -XX:-UseBiasedLocking
 *        耗时 186ms
 */
public class LockEliminate  {

    private  static final int cicyle = 2000000;

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();

        for (int i = 0; i < cicyle; i++) {
            createStringBuffer("jvm","锁销除");
        }

        long end = System.currentTimeMillis();
        System.out.println("耗时"+(end-begin));

    }

    public static String createStringBuffer(String s1,String s2){
        StringBuffer sb = new StringBuffer();
        sb.append(s1);
        sb.append(s2);
        return sb.toString();
    }
}


