package MultiplexingClass;

import java.util.Random;

/**
 * Created by wangbo on 2018/9/4.
 */
public class FinalData {
    private static Random random = new Random(47);
    private String id;
    public FinalData(String id) {
        this.id = id;
    }
    //编译期常量 can be compile-time constants
    private final int valueOne = 9;
    private static final int VALUE_TWO = 99;
    public static final int VALUE_THREE = 39;
    //can not be compile-time constants
    private final int i4 = random.nextInt(20); //运行时生成的值
    static final int INT_5 = random.nextInt(20);//值不会变化 因为加载的时候已经初始化 而不是每次创建时初始化
    private Value v1 = new Value(11);
    private final Value v2 = new Value(22);
    private static final Value VALUE_3 = new Value(33);
    //Arrays
    private final int[] a = {1,2,3,4,5,6};

    @Override
    public String toString() {
        return id + ": i4 = " + i4 +", INT_5 = "+  INT_5;
    }

    public static void main(String[] args) {
        FinalData fd1 = new FinalData("fd1");
        //fd1.valueOne ++;
        fd1.v2.i ++;
        fd1.v1 = new Value(9);
        for (int i = 0; i < fd1.a.length; i++) {
            fd1.a[i] ++;
        }
        //fd1.v2 = new Value(0)；
        //fd1.VALUE_3 = new Value(1);
        //fd1.a = new int[3];
        System.out.println(fd1);
        FinalData fd2 = new FinalData("fd2");
        System.out.println(fd1);
        System.out.println(fd2);
    }

}

final class Value {
    int i;
    public Value(int i) {
        this.i = i;
    }
}
