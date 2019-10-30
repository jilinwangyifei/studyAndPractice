package InnerClass;

/**
 * Created by wangbo on 2018/11/3.
 */
public class DotThis {
    void f() {
        System.out.println("DotThis.f()");
    }

    public class Inner {
        public DotThis  outer() {
            return DotThis.this;
        }
    }

    public Inner inner() {
        return  new Inner();
    }

    public static void main(String[] args) {
        DotThis dt = new DotThis();
        dt.inner().outer().f();
    }
}

