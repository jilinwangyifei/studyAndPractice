package Basic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by wangbo on 2018/11/11.
 * 参考 https://www.cnblogs.com/daxin/p/3366606.html
 */
public class UnsafeStudy {

    int value = 0;

    public static void main(String[] args) throws Exception {
        long objectOffset = getUnsafeInstance().objectFieldOffset
                (UnsafeStudy.class.getDeclaredField("value"));
        System.out.println(objectOffset);
    }

    private static Unsafe getUnsafeInstance() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);
        return (Unsafe) theUnsafeInstance.get(Unsafe.class);
    }

}
