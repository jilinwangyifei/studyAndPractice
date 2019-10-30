package Reference;

/**
 * Created by wangbo on 2018/3/19.
 */
public class StrongRef {

    public static void main(String[] args) {
        StringBuffer str = new StringBuffer("Hello world");
        StringBuffer str1 = str;
        System.out.println(str == str1);
        //== 表示两操作数指向的空间地址是否一致

        String string1 = "123";
        String string2 = string1;
        System.out.println(string1 == string2);
    }

}
