package Key;

/**
 * Created by wangbo on 2018/8/11.
 */
public class Assert {
    //参数-ea开启断言开关
    public static void main(String[] args) {

        assert true;
        System.out.println("断言1没有问题");

        assert false : "断言失败";
        System.out.println("断言2没有问题");
    }
}
