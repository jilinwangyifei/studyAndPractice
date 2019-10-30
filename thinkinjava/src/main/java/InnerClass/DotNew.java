package InnerClass;

/**
 * Created by wangbo on 2018/11/3.
 */
public class DotNew {
    public class Inner {}

    public static void main(String[] args) {
        DotNew dotNew = new DotNew();
        Inner inner = dotNew.new Inner();
    }
}
