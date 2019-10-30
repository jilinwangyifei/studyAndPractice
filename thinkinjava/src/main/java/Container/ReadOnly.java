package Container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by wangbo on 2018/9/22.
 *
 * 不可修改的
 */
public class ReadOnly {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");

        List<String> a = Collections.unmodifiableList(new ArrayList<String>(list));
        System.out.println(a);
        a.add("d");//unsupportedOperationException
    }
}

