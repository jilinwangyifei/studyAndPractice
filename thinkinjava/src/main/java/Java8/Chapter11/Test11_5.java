package Java8.Chapter11;
import java.util.concurrent.CompletableFuture;

public class Test11_5 {
    //响应 Completable的completion事件

    void  completionPrice() {
            Test11_4 test = new Test11_4();
            CompletableFuture [] pricesStreamArray =
                    test.findPricesStream("").map(f -> f.thenAccept(System.out::println))
                    .toArray(size -> new CompletableFuture[size]);
            CompletableFuture.allOf(pricesStreamArray).join();
    }

}
