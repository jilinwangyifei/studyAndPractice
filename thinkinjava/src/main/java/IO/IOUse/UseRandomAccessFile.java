package IO.IOUse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by wangbo on 2018/9/6.
 */
public class UseRandomAccessFile {
    static String file = "ss.dat";
    static void display() throws IOException {
        RandomAccessFile ram = new RandomAccessFile(file,"r");
        for (int i = 0; i < 7 ; i++)
            System.out.println(ram.readDouble());
        System.out.println(ram.readUTF());
        ram.close();
    }

    public static void main(String[] args) throws IOException{
        RandomAccessFile ram = new RandomAccessFile(file,"rw");
        for (int i = 0; i < 7 ; i++) {
            ram.writeDouble(i*1.77);
        }
        ram.writeUTF("the end of the file");
        ram.close();
        display();

        ram = new RandomAccessFile(file,"rw");
        ram.seek(5*8);
        ram.writeDouble(47.0001);
        ram.close();
        display();
    }
}
