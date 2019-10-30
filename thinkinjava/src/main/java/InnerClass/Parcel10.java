package InnerClass;

/**
 * Created by wangbo on 2018/11/3.
 */
public class Parcel10 {
    public Destination destination(final String dest, final float price) {
        return new Destination() {
            //  *** 匿名类中使用的参数必须是final的 ***
            private int cost;

            {
                cost = Math.round(price);
                if (cost > 100) System.out.println("out budget");
            }

            private String label = dest;
            @Override
            public String readLabel() {
                return label;
            }
        };
    }

    public static void main(String[] args) {
        Parcel10 P = new Parcel10();
        Destination d = P.destination("ss",10.00f);
    }
}
