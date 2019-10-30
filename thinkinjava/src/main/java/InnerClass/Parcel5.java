package InnerClass;

/**
 * Created by wangbo on 2018/11/3.
 *
 * 方法内部类
 */
public class Parcel5 {
    public Destination destination(String s) {
        class PDestination implements Destination {
            private String label;
            public PDestination (String whereTo){
                label = whereTo;
            }
            @Override
            public String readLabel() {
                return label;
            }
        }
        return new PDestination(s);
    }

    public static void main(String[] args) {
        Parcel5 p = new Parcel5();
        Destination destination = p.destination("Tasmania");
        System.out.println(destination.readLabel());
    }

}
