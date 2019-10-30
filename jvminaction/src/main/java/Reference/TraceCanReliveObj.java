package Reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * Created by wangbo on 2018/3/19.
 *
 * 虚引用 所有引用类型最弱的一个 一个持有虚引用的对象 和没有引用一样 随时可能被回收期回收
 *       虚引用必须和引用队列一起使用 它的作用在于追踪垃圾回收过程
 *
 */
public class TraceCanReliveObj {

    private static TraceCanReliveObj obj;

    static ReferenceQueue<TraceCanReliveObj> plantomQueue = null;

    public static class checkRefQueue extends Thread{
        @Override
        public void run() {
            while (true){
                if(plantomQueue != null){
                    PhantomReference<TraceCanReliveObj> objt = null;
                    try {
                        objt = (PhantomReference<TraceCanReliveObj>)plantomQueue.remove();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (objt != null) {
                        System.out.println("TraceCanReliveObj is delete by GC");
                    }
                }
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("TraceCanReliveObj finalize called");
        obj = this;
    }

    public static void main(String[] args) throws Exception{
        Thread t = new checkRefQueue();
        t.setDaemon(true);
        t.start();

        plantomQueue = new ReferenceQueue<TraceCanReliveObj>();

        obj = new TraceCanReliveObj();

        PhantomReference<TraceCanReliveObj> plantomRef =
                new PhantomReference<TraceCanReliveObj>(obj,plantomQueue);

        obj = null;
        System.gc();
        Thread.sleep(1000);
        if (obj == null) {
            System.out.println("obj 是 null");
        } else {
            System.out.println("obj 可用");
        }
        System.out.println("第二次 gc");
        obj = null;
        System.gc();
        Thread.sleep(1000);
        if (obj == null) {
            System.out.println("obj 是 null");
        } else {
            System.out.println("obj 可用");
        }
    }
}
