package IO;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * Created by wangbo on 2018/9/3.
 */
public class UsingBuffers {

    private static void symmetricScramble(CharBuffer buffer){
        while (buffer.hasRemaining()) {
            //标记position的值
            buffer.mark();
            char c1 = buffer.get();
            char c2 = buffer.get();
            //rest把position的值设置为mark
            buffer.reset();
            buffer.put(c2).put(c1);
        }
    }

    public static void main(String[] args) {
        char[] data = "usingBuffers".toCharArray();
        ByteBuffer bb = ByteBuffer.allocate(data.length*2);
        CharBuffer cb = bb.asCharBuffer();
        cb.put(data);

        System.out.println(cb.rewind());
        symmetricScramble(cb);
        System.out.println(cb.rewind());
        symmetricScramble(cb);
        System.out.println(cb.rewind());
    }

}
