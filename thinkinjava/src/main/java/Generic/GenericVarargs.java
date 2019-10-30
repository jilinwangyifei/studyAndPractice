package Generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangbo on 2018/10/3.
 */
public class GenericVarargs {
    public static <T> List<T> makeList (T... args) {
        List<T> arrayList = new ArrayList<T>();
        for (T items : args) {
           arrayList.add(items);
        }
        return arrayList;
    }

    public static void main(String[] args) {
        List<String> ls = makeList("a");
        System.out.println(ls);
        ls = makeList("abcdefg".split(""));
        System.out.println(ls);
    }
}
