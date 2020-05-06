package Java8.Chapter11;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Test11_2 {

    //实现异步api

    //11.2.1 将同步访问变为异步方法
    void test11_2_1() {

    }

    public Future<Double> getPriceAsyn (String product) {
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        new Thread( () ->
            {
                try {
                    double price = 0;
                    completableFuture.complete(price);
                }catch (Exception e) {
                    completableFuture.completeExceptionally(e);// 抛出异常
                }
            }
        ).start();
        return completableFuture;
    }

    //11.2.2 错误处理
    void test11_2_2() {

    }


    public Future<Double> getPriceAsyn2 (String product) {
        return CompletableFuture.supplyAsync(()-> calculatePrice(product) );
    }

    double calculatePrice  (String product) {
        return 0.00;
    }
}
