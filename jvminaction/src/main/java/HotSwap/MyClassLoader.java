package HotSwap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Created by wangbo on 2018/3/12.
 */
public class MyClassLoader extends ClassLoader {

    private String fileName;

    MyClassLoader(String fileName){
        this.fileName = fileName;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {

        Class clazz = this.findLoadedClass(className);
        if(null == clazz){
            try{
                String classFile = getClassFile(className);
                FileInputStream fis = new FileInputStream(classFile);
                FileChannel fileC = fis.getChannel();
                ByteArrayOutputStream baos =  new ByteArrayOutputStream();
                WritableByteChannel outc = Channels.newChannel(baos);
                ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                while (true){
                     int i = fileC.read(buffer);
                     if(i == 0 || i == -1){
                        break;
                     }
                     buffer.flip();
                     outc.write(buffer);
                     buffer.clear();
                }
                fis.close();
                byte[] bytes = baos.toByteArray();

                clazz = defineClass(className,bytes,0,bytes.length);
            }catch (Exception e){

            }
        }
        return  clazz;
    }

    private String getClassFile(String name) {
        StringBuffer sb = new StringBuffer(fileName);
        name = name.replace('.', File.separatorChar) + ".class";
        sb.append(File.separator + name);
        return sb.toString();
    }

    public static void main(String[] args) {
        for (int uid = 0; uid < 1000; uid++) {
            System.out.println(uid+",库"+ ((uid / 10) % 8 + 1) +"表"+ uid % 10 +",新库"+ ((uid / 10) % 16 + 1)+"表"+uid % 10);
        }

    }
}
