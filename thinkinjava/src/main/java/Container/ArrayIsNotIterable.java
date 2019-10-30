package Container;

import java.util.Arrays;

/**
 * Created by wangbo on 2018/10/1.
 */
public class ArrayIsNotIterable {
    static <T> void test(Iterable<T> iterable) {
        for (T t : iterable) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {
        test(Arrays.asList(1,2,3));
        String[] strings = new String[] {"A","B","C"};
        //test(strings);
        //尝试把数组当做一个Iterable做参数会导致失败 说明不存在从数组到Iterable的自动转换
        test(Arrays.asList(strings));
    }
}
