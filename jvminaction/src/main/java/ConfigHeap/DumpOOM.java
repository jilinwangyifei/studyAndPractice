package ConfigHeap;

import java.util.Vector;

/**
 * Created by wangbo on 2018/3/15.
 *
 * -Xmx20m -Xms5m +HeapDumpOnOutOfMemeryError  -XX:HeapDumpPath=oom.dump
 */
public class DumpOOM {

    public static final String ss = "asd";

    public static void main(String[] args) {
        Vector vector = new Vector();
        for (int i = 0; i < 25; i++) {
            vector.add(new byte[1*1024*1024]);
        }
    }
}
