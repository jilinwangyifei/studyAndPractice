package Generic;

/**
 * Created by wangbo on 2018/10/3.
 */
public class ThreeTuple<A, B, C> extends TwoTuple {
    public final C c;

    ThreeTuple(A a, B b, C c) {
        super(a, b);
        this.c = c;
    }

    @Override
    public String toString() {
        return "(" + a + ","+ b +","+ c +")";
    }
}
