package DesignPattern.JdkFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by wangbo on 2018/3/4.
 */
public class RealData implements Callable<String> {

    public String para;

    public RealData(String para){
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        StringBuffer str = new StringBuffer();
        for(int i = 0; i < 10; i++){
            str.append(para);
            try{
                Thread.sleep(1000);
            }catch (Exception e){

            }
        }
        return str.toString();
    }

    public static void main(String[] args) throws Exception{
        //构建futureTask
        FutureTask<String> futureTask = new FutureTask<String>(new RealData("name"));
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        //执行futureTask,相当上例中的client.request()发送请求
        //这里开启线程执行realData的call()执行
        executorService.submit(futureTask);
        System.out.println("请求完毕");
        try{
            //在这里模拟一个耗时的工作 代替其他的业务操作
            Thread.sleep(1000);
        }catch (Exception e){

        }
        //如果call()方法没有执行完成 则依然会等待
        System.out.println("数据= "+futureTask.get());

    }
}
