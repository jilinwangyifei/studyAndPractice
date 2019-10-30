package ThreadPool;

import java.util.concurrent.*;

/**
 * Created by wangbo on 2018/3/6.
 *
 * 线程池原理参考 http://www.kuqin.com/shuoit/20160829/352799.html
 *
 */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor {

   public  TraceThreadPoolExecutor(int corePoolSize, int maxPoolSize, long keepAliveTime,
                                   TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue){
       super(corePoolSize,maxPoolSize,keepAliveTime,timeUnit,blockingQueue);
   }

    @Override
    public void execute(Runnable command) {
        super.execute(wrap(command,clientTrace(),Thread.currentThread().getName()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(task);
    }

    private Exception clientTrace(){
       return  new Exception("client stack trace");
    }

    private Runnable wrap(final Runnable runnable,final Exception clientStack,String clientThreadName){
        return new Runnable() {
            @Override
            public void run() {
                try{
                    runnable.run();
                }catch (Exception e){
                    clientStack.printStackTrace();
                    e.printStackTrace();
                }
            }
        };
    }


    static class DriverTask implements Runnable{
        int a,b;
        DriverTask(int a,int b){
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            double result = a/b;
            System.out.println(""+result);
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new TraceThreadPoolExecutor(0,Integer.MAX_VALUE,0,
                TimeUnit.SECONDS,new SynchronousQueue<Runnable>(false));

        /**
         * 错误堆栈中可以看出是哪里提交的任务
         */
        for (int i = 0; i < 5 ; i++) {
            //threadPoolExecutor.execute(new DriverTask(100,i));
            threadPoolExecutor.submit(new DriverTask(100,i));
        }
    }
}
