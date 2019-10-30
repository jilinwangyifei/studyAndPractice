package IO.IOUse;

import java.io.StringReader;
/**
 * Created by wangbo on 2018/9/5.
 */
public class MemoryInput {

    public static void main(String[] args) throws Exception {

        StringReader stringReader = new StringReader(
                 BufferedInputFile.read("/BufferedInputFile.java"));
        int c;
        while ((c = stringReader.read()) != -1) {
            System.out.println((char) c);
        }
    }
}
