package LockAndConcurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangbo on 2018/3/17.
 *
 * 锁在应用层的优化思路
 * 1.减少锁持有时间
 * 2.减少锁粒度 ConcurrentHashMap
 * 3.锁分离     LinkedBlockingQueue
 * 4.锁粗化
 *
 * 锁在jvm层的优化  // TODO: 2018/10/24
 * 锁粗化
 * 锁销除
 * 轻量级锁
 * 偏向锁
 * 适应性自旋
 */
public class LockOptimization {

    //减少锁持有时间
    public synchronized void syncMethod(){
       /* otherCode1();
        mutexMethod();
        otherCode2();*/
    }

    public synchronized void syncMethod2(){
       /* otherCode1();
          synchronized(this){
             mutexMethod();
          }
          otherCode2();*/
    }

    //----------------------------------------

    //锁粗化
    public void demoLock(){
        synchronized (this){
            //dosomeThing
        }
        synchronized (this){
            //dosomeThing
        }
    }
    public void demoLock2(){
        synchronized (this){
            //dosomeThing
        }
    }

    public void demoLock3(){
        for (int i = 0; i < i; i++) {
            synchronized (this){

            }
        }
        //将上面for循环修改
        synchronized (this){
            for (int i = 0; i < i; i++) {

            }
        }

    }
    //------------------------------------------

}
