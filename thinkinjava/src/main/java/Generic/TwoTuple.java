package Generic;

/**
 * Created by wangbo on 2018/10/3.
 */
public class TwoTuple<A, B> {
    public final A a;
    public final B b;

    public TwoTuple(A a, B b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "(" + a + ","+ b +")";
    }
}
