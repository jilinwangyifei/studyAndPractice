package Memory;

/**
 * Created by wangbo on 2018/4/2.
 *
 */
public class ThreadMmAndMainMm {

    /*
    *   （lock - unlock） lock将一个主内存变量标记成线程独占，
    *                     unlock将独占的变量释放
    *   （read - load）   read 将主内存的变量读取到CPU中，
    *                    load操作将read到的变量存入到工作内存中,一定会成对出现
    *   （use - assign）  use将工作内存中的变量传递给执行的代码中，当代码需要使用变量值的字节码时，需要这个操作。
    *                    assign 赋值操作，将代码中赋值指令出现时，把收到的变量赋值到工作内存中
    *    (store - write) store 将工作内存的变量传送回主内存，但是只是传送，
    *                    write操作才会将值写入到主内存。而且这两个一定会成对出现
    */

}
