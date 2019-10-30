package InnerClass;

/**
 * Created by wangbo on 2018/11/3.
 */
public class Parcel7b {
    class myContents implements Contents {
        @Override
        public int value() {
            return 0;
        }
    }

    public Contents contents(){
        return new myContents();
    }

    public static void main(String[] args) {
        Parcel7b parcel7b = new Parcel7b();
        parcel7b.contents();
    }
}
