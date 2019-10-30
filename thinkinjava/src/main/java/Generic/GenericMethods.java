package Generic;

/**
 * Created by wangbo on 2018/10/3.
 * 尽量使用泛型方法 对于static的方法 无法访问类的类型参数
 * 所以 static方法拥有泛型能力 必须使其成为泛型方法
 */
public class GenericMethods {
    public <T> void f(T x) {
        System.out.println(x.getClass().getName());
    }

    public static void main(String[] args) {
        GenericMethods gm = new GenericMethods();
        gm.f(" ");
        gm.f(1);
        gm.f(1.0);
        gm.f(1.0f);
        gm.f('c');
    }
}
