package Reference;

import java.lang.ref.WeakReference;

/**
 * Created by wangbo on 2018/3/19.
 *
 * 弱引用 在系统gc时 不管系统堆空间使用情况如何 都会将对象进行回收
 */
public class WeakRef {
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
        WeakReference<User> weakReference = new WeakReference<User>(u);
        u = null;
        System.out.println(weakReference.get());
        System.gc();
        System.out.println("After gc");
        System.out.println(weakReference.get());
    }
}
