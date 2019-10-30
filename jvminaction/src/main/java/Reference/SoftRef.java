package Reference;

import java.lang.ref.SoftReference;

/**
 * Created by wangbo on 2018/3/19.
 *
 *  一个对象只持有软引用 当空间不足时 就会被回收
 *  gc未必会回收软引用的对象 但是 当内存紧张时 软引用对象就会被回收
 *
 */
public class SoftRef {

    public static class User{

        int id;
        String name;

        public User(int id,String name){
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "id="+id+"name="+name;
        }
    }

    public static void main(String[] args) {
        User u = new User(1,"geym");
        SoftReference<User> softReference = new SoftReference<User>(u);
        u = null;

        System.out.println(softReference.get());
        System.gc();
        System.out.println("after gc");
        System.out.println(softReference.get());

        byte[] b = new byte[1024*1024*3];
        System.gc();
        System.out.println(softReference.get());

    }

}
