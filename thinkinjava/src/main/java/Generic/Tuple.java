package Generic;

/**
 * Created by wangbo on 2018/10/3.
 */
public class Tuple {
    public static <A,B> TwoTuple<A,B> tuple(A a, B b) {
        return new TwoTuple<A,B>(a,b);
    }
}
