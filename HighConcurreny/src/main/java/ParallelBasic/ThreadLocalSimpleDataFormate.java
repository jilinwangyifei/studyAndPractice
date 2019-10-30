package ParallelBasic;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangbo on 2018/3/8.
 */
public class ThreadLocalSimpleDataFormate {

    //ThreadLocal中的ThreadLocalMap 使用开放定址法(线性探测)查找元素
    //开放定址法(1.线性探测 2.二次探测 3.随机探测)

    static ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<SimpleDateFormat>();

    public static class ParseDate implements  Runnable{

        int i = 0;
        public ParseDate(int i){
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if(t1.get() == null){
                    t1.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                }
                Date date = t1.get().parse("2015-10-12 11:11:"+i%60);
                System.out.println(i+","+date);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(new ParseDate(i)).start();
        }
    }
}
