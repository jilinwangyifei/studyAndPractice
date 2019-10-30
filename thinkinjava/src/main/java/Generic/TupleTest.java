package Generic;

/**
 * Created by wangbo on 2018/10/3.
 */
public class TupleTest {
    static TwoTuple<String, String> twoTuple(){
        return new TwoTuple<>("one","two");
    }

    static ThreeTuple<String, String, String> threeTuple(){
        return new ThreeTuple<>("one","two", "three");
    }

    public static void main(String[] args) {
        System.out.println(twoTuple());
        System.out.println(threeTuple());
    }
}
