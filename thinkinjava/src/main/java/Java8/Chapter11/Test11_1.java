package Java8.Chapter11;

import java.util.concurrent.*;

public class Test11_1 {

    //11.1 Future接口
    void test11_1 () {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future future = executor.submit( () -> {
            //dosomeThing
        });

        try {
            future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e){

        } catch (ExecutionException e) {

        } catch (TimeoutException e) {

        }
    }

    //11.1.1 future 接口的局限性

    //11.1.2 使用completableFuture构建应用


}
