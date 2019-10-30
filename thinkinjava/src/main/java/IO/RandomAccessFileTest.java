package IO;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by wangbo on 2018/7/16.
 */
public class RandomAccessFileTest {

    public static void main(String[] args) throws Exception {

        //limit     写模式下 最多能往buffer写多少数据 写模式下 limit等于buffer的capacity
        //          读模式下 limit表示你能读到多少数据 切换读模式时 limit被设置为写模式下的position值
        //position  写模式下 position表示当前的位置 初始值0 当一个byte long插入后 position移动到下一个可插入的位置 最大值capacity-1
        //          读模式下 position重置为0 当从buffer的position读取数据时 移动到下一个可读的位置
        //capacity  buffer中一共能包含的字节数
        //
        //rewind()  将position设置为0 重读buffer中的所有数据 limit保持不变 仍能表示从buffer中读取多少数据
        //mark()    可以标记特定的position
        //reset()   恢复到mark的position
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/wangbo/Downloads/study/LearningOfThinkInJava-master/Thinkinjava/src/main/java/IO/file/hello1.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(48);

        int byteRead = fileChannel.read(buffer);

        while (byteRead != -1) {
            //flip方法将Buffer从写模式切换到读模式。调用flip()方法会将position设回0，并将limit设置成之前position的值
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.println((char) buffer.get());
            }
            //两种清空方式
            buffer.clear();  //清空整个缓存区
            buffer.compact();//只清除已读的数据
            byteRead = fileChannel.read(buffer);
        }
        randomAccessFile.close();


        /*RandomAccessFile rf = new RandomAccessFile("rtest.dat", "rw");
        for (int i = 0; i < 10; i++) {
            //写入基本类型double数据
            rf.writeDouble(i * 1.414);
        }
        rf.close();
        rf = new RandomAccessFile("rtest.dat", "rw");
        //直接将文件指针移到第5个double数据后面
        rf.seek(5 * 8);
        //覆盖第6个double数据
        rf.writeDouble(47.0001);
        rf.close();
        rf = new RandomAccessFile("rtest.dat", "r");
        for (int i = 0; i < 10; i++) {
            System.out.println("Value " + i + ": " + rf.readDouble());
        }
        rf.close();*/

    }
}
