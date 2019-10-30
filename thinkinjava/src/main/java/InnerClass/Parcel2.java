package InnerClass;

/**
 * Created by wangbo on 2018/10/22.
 */
public class Parcel2 {
    class Contents {
        private int i = 11;
        private int value() {
            return i;
        }
    }

    class Destination {
        private String label;
        Destination(String label) {
            this.label = label;
        }
        String readLabel() {
            return label;
        }
    }

    public Destination to(String s) {
        return new Destination(s);
    }

    public Contents contents() {
        return new Contents();
    }

    public void ship(String dest) {
        Contents c = new Contents();
        Destination d = new Destination(dest);
        System.out.println(d.readLabel());
    }

    public static void main(String[] args) {
        Parcel2 p = new Parcel2();
        p.ship("Tesmaina");
        Parcel2 q = new Parcel2();
        Parcel2.Contents c =  p.contents();
        Parcel2.Destination d = q.to("Borneo");
    }
}
