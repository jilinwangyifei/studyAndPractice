package InnerClass;

/**
 * Created by wangbo on 2018/11/3.
 */
public class Parcel7 {
    //创建一个继承自Contents的匿名类的对象
    //通过new 表达式返回的引用被自动向上转型为对Contents的引用
    public Contents contents (){
        return new Contents() {
            @Override
            public int value() {
                return 0;
            }
        };
    }

    public static void main(String[] args) {
        Parcel7 p = new Parcel7();
        Contents c = p.contents();
    }
}
