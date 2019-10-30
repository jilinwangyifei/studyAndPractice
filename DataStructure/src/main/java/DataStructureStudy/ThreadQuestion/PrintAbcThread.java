package DataStructureStudy.ThreadQuestion;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by wangbo on 2018/3/30.
 *
 * https://blog.csdn.net/wyyayy/article/details/51888371
 */
public class PrintAbcThread extends  Thread {

    String name;
    PrintAbcThread nextThread;
    AtomicBoolean ifPrint =  new AtomicBoolean();

    PrintAbcThread(String name,boolean ifPrint){
        this.name = name;
        this.ifPrint.set(ifPrint);
    }

    public void setNextThread(PrintAbcThread nextThread) {
        this.nextThread = nextThread;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0){
            if (ifPrint.compareAndSet(true,false)){
                count --;
                System.out.println(name);
                nextThread.ifPrint.set(true);
            }
        }
    }

    public static void main(String[] args) {
         PrintAbcThread threadA = new PrintAbcThread("A",true);
         PrintAbcThread threadB = new PrintAbcThread("B",false);
         PrintAbcThread threadC = new PrintAbcThread("C",false);

         threadA.setNextThread(threadB);
         threadB.setNextThread(threadC);
         threadC.setNextThread(threadA);

         threadA.start();
         threadB.start();
         threadC.start();
    }

}
