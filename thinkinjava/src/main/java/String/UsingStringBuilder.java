package String;

import java.io.RandomAccessFile;
import java.util.Random;

/**
 * Created by wangbo on 2018/8/26.
 */
public class UsingStringBuilder {

    public static Random random = new Random(47);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < 25 ; i++) {
            sb.append(random.nextInt(1000));
            sb.append(",");
        }
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) {
        UsingStringBuilder usb = new UsingStringBuilder();
        System.out.println(usb);
    }
}
