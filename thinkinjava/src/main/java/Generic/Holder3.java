package Generic;

/**
 * Created by wangbo on 2018/10/3.
 */
public class Holder3<T> {
    private T t;
    public Holder3(T t) {
        this.t = t;
    }
    public void set(T t) {
        this.t = t;
    }
    public T get() {
        return t;
    }
}
