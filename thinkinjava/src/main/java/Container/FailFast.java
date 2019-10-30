package Container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by wangbo on 2018/9/22.
 * 快速报错
 */
public class FailFast {
    public static void main(String[] args) {
        Collection<String> c = new ArrayList<String>();
        Iterator<String> it = c.iterator();
        c.add("an Object");
        try {
            String s = it.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
