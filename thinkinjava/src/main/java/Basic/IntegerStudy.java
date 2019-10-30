package Basic;

/**
 * Created by wangbo on 2018/9/16.
 */
public class IntegerStudy {

    public static void main(String[] args) {
        /**
         * 在通过valueOf方法创建Integer对象的时候，如果数值在[-128,127]之间
         * 便返回指向IntegerCache.cache中已经存在的对象的引用；否则创建一个新的Integer对象。
         */
        Integer i = 200;
        int j = 200;
        System.out.println(i == j);

        Integer k = 200;
        System.out.println(i == k);

        Integer x = 127;
        Integer y = 127;
        System.out.println(x == y);

        int x1 = 127;
        int y1 = 127;
        System.out.println(x1 == y1);

        Integer m1 = 128;
        Integer n1 = 128;
        System.out.println(m1 == n1);

        Integer m = 128;
        Integer n = 128;
        System.out.println(m == n);

        System.out.println(1+'1');
        System.out.println(1+"1");
    }
}
