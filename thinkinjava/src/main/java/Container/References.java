package Container;

import java.lang.ref.*;
import java.util.LinkedList;

/**
 * Created by wangbo on 2018/9/15.
 */
public class References {

    private static ReferenceQueue<VeryBig> rq = new
            ReferenceQueue<VeryBig>();

    private static void checkQueue() {
        Reference<? extends VeryBig> inq = rq.poll();
        if (inq != null) {
            System.out.println("in queue : " + inq.get());
        }
    }

    public static void main(String[] args) {
        int size = 10;
        if (args.length > 10) size = new Integer(args[0]);

        //软引用
        LinkedList<SoftReference<VeryBig>> sa = new LinkedList<SoftReference<VeryBig>>();
        for (int i = 0; i < size; i++) {
            sa.add(new SoftReference<VeryBig>(
                        new VeryBig("soft"+i)));
            System.out.println("just created " + sa.getLast());
            checkQueue();
        }

        //弱引用
        LinkedList<WeakReference<VeryBig>> wa = new LinkedList<WeakReference<VeryBig>>();
        for (int i = 0; i < size; i++) {
            wa.add(new WeakReference<VeryBig>(
                    new VeryBig("weak"+i)));
            System.out.println("just created " + wa.getLast());
            checkQueue();
        }

        SoftReference<VeryBig> s = new SoftReference<VeryBig>(new VeryBig("soft"));
        WeakReference<VeryBig> w = new WeakReference<VeryBig>(new VeryBig("weak"));
        System.gc();

        //虚引用
        LinkedList<PhantomReference<VeryBig>> pa = new LinkedList<PhantomReference<VeryBig>>();
        for (int i = 0; i < size; i++) {
            pa.add(new PhantomReference<VeryBig>(
                    new VeryBig("pathom"+i),rq));
            System.out.println("just created " + pa.getLast());
            checkQueue();
        }

    }
}

class VeryBig {

    private static final int SIZE = 10000;
    private long[] la = new long[SIZE];
    private String ident;
    public VeryBig(String id) {
        ident = id;
    }

    @Override
    public String toString() {
        return ident;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize "+ident);
    }
}
