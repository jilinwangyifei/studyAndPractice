package InnerClass;

/**
 * Created by wangbo on 2018/11/3.
 *
 * 匿名类的基类需要一个有参数的构造器
 */
public class Parcel8 {
    public Wrapping wrapping(int x) {
        return new Wrapping(x) {
            @Override
            public int value() {
                return super.value();
            }
        };//此分号标记的是表达式的结束
    }

    public static void main(String[] args) {
        Parcel8 p = new Parcel8();
        Wrapping wrapping = p.wrapping(10);
    }
}

class Wrapping {
    private int i;
    public Wrapping(int x){
        i = x;
    }
    public int value() {
        return i;
    }
}
