package ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangbo on 2018/3/7.
 */
public class ExtThreadPool {

    public static class MyTask implements Runnable{

        public String name;

        public MyTask(String name){
            this.name = name;
        }
        @Override
        public void run() {
            System.out.println("正在执行 threadid"+Thread.currentThread().getId()+",name"+Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            }catch (Exception E){

            }
        }

    }


    public static void main(String[] args) throws InterruptedException{

        //实现线程池执行方法前操作 方法后操作 线程池结束任务后操作
        ExecutorService executorService = new ThreadPoolExecutor(5,5,0l, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>()){

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行"+((MyTask)r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成"+((MyTask)r).name);
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };

        for (int i = 0; i < 5 ; i++) {
            MyTask myTask = new MyTask("TASK-GEYM-"+i);
            executorService.execute(myTask);
            Thread.sleep(100);
        }

        executorService.shutdown();
    }
}
