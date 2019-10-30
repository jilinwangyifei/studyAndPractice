package String;

/**
 * Created by wangbo on 2018/9/2.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 无意识的递归
 * 编译器看到+号试图将InfiniteRecursion转成string 调用toString方法 发生递归调用
 */
public class InfiniteRecursion {
    @Override
    public String toString() {
        return "InfiniteRecursion address :" + this + "\n";
    }

    public static void main(String[] args) {
        List<InfiniteRecursion> list = new ArrayList<InfiniteRecursion>();
        for (int i = 0; i < 10; i++)
            list.add(new InfiniteRecursion());
        System.out.println(list);
    }
}
