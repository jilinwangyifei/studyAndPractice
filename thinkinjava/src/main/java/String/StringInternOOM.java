package String;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangbo on 2018/3/16.
 * -Xmx5m -XX:MaxPermSize=5m
 */
public class StringInternOOM {

    public static void main(String[] args) {
        List<String> list = new ArrayList();
        int i = 0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }
}
