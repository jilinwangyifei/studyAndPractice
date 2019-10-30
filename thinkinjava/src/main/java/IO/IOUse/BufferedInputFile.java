package IO.IOUse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by wangbo on 2018/9/5.
 */
public class BufferedInputFile {

    public static String read (String fileName) throws Exception{
        BufferedReader in = new BufferedReader(
                new FileReader(fileName));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine()) != null)
            sb.append(s+"\n");
        in.close();
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(read("BufferedInputFile.java"));
    }
}
