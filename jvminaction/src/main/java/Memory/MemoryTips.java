package Memory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangbo on 2018/9/21.
 *
 * 配置 -Xms16m -Xmx32m
 *
 * 出处
 * 一个进程中 一个线程oom了会不会影响其他线程的工作
 *    oom时如果线程能够正常的处理这个异常情况 比如不在申请更多的内存或者其他资源
 *    或者放弃那个子任务或者子线程 系统oom可以回到正常情况
 */
public class MemoryTips {

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                List<byte[]> list = new ArrayList<byte[]>();
                while (true) {
                    byte[] b = new byte[1024*1024];
                    list.add(b);
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e) {

                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(new Date().toString()+Thread.currentThread());
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e) {

                    }
                }

            }
        }.start();

    }
}
