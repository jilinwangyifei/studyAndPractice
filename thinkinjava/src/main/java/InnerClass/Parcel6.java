package InnerClass;

/**
 * Created by wangbo on 2018/11/3.
 */
public class Parcel6 {

    public void internalTracking(boolean b) {
        //TrackingShip 在if的作用域中
        if (b) {
            class TrackingShip {
                private String id;
                public TrackingShip(String s) {
                    id = s;
                }
                String getShip() {
                    return id;
                }
            }
            TrackingShip trackingShip = new TrackingShip("slip");
            String s = trackingShip.getShip();
        }
        //out of scope
        //TrackingShip trackingShip2 = new TrackingShip("slip");

    }

    public void track() {
        internalTracking(true);
    }

    public static void main(String[] args) {
        Parcel6 p = new Parcel6();
        p.track();
    }
}
