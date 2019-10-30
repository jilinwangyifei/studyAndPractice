package MultiplexingClass;

/**
 * Created by wangbo on 2018/9/4.
 */
public class FinalArguments {

    void with(final Gizmo gizmo) {
        //gizmo = new Gizmo();
    }

    void withOut( Gizmo gizmo) {
        gizmo = new Gizmo();
        gizmo.spin();
    }

    void f(final int i) {
        //i++;
    }

    int g(final int i) {
        return i + 1;
    }

    public static void main(String[] args) {
        FinalArguments finalArguments = new FinalArguments();
        finalArguments.withOut(null);
        finalArguments.with(null);
    }
}


class Gizmo {
    public void spin(){}
}