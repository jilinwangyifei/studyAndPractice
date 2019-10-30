package MultiplexingClass;

/**
 * Created by wangbo on 2018/9/4.
 *
 * final类禁止继承 他其中的所有方法隐式为final的 因此无法覆盖他们
 */
public class FinalClass {

    public static void main(String[] args) {
        Dinosaur dinosaur = new Dinosaur();
        dinosaur.f();
        dinosaur.i = 12;
        dinosaur.j++    ;
    }
}

final class Dinosaur{
    int i = 7;
    int j =     8;
    SmallBrain smallBrain = new SmallBrain();
    void f() {}
}

class SmallBrain{

}
