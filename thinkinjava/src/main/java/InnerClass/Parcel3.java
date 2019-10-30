package InnerClass;

/**
 * Created by wangbo on 2018/11/3.
 */
public class Parcel3 {
    class Contents {
        private int i = 11;
        private int value() {
            return i;
        }
    }

    class Destination {
        private String label;

        Destination(String whereTo) {
            this.label = whereTo;
        }

        private String ReadLabel(){
            return label;
        }
    }

    public static void main(String[] args) {
        Parcel3 p = new Parcel3();
        Parcel3.Contents contents = p.new Contents();
        Parcel3.Destination destination = p.new Destination("SiKaBuLuo");
    }
}
