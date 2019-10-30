package Java8.Chapter11;

import lombok.Data;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Test11_3 {

    List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"));


    public List<String> findPrices(String product) {
        List<CompletableFuture<String>> priceFuture =
                shops.stream().map(
                        shop ->
                            CompletableFuture.supplyAsync(() ->
                                    shop.getName() + " price is " +
                                    shop.getPrice(product))
                ).collect(Collectors.toList());

        return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }


    //11.3.4 使用定制的执行器
    void test11_3_4() {
        //修改supplyAsync第三个参数 传入线程池
    }

}

@Data
class Shop {

   private String name;

   Shop (String mame) {
       this.name = name;
   }

   double getPrice(String product) {
       return 0;
   }
}