package Java8.Chapter11;

import lombok.Data;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import static java.util.stream.Collectors.toList;

public class Test11_4 {

    List<Shop_02> shops = Arrays.asList(new Shop_02("BestPrice"),
            new Shop_02("LetsSaveBig"),
            new Shop_02("MyFavoriteShop"),
            new Shop_02("BuyItAll"));

    ExecutorService ex;

    //11.4.3 构造同步he异步操作
    public List<String> findPrices(String product) {
        List<CompletableFuture<String>> priceFutures =
            shops.stream()
                .map(shop ->
                        CompletableFuture.supplyAsync(
                                () -> shop.getPrice(product), ex)
                )
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(
                    quote -> CompletableFuture.supplyAsync(
                        () -> Discount.applyDiscount(quote), ex)))
                .collect(toList());

        return priceFutures.stream().map(CompletableFuture::join).collect(toList());
    }

    //11.4.4 将两个CompletableFuture整合起来无论他们是否有依赖
    void test11_4_4() {

    }
}

enum Code {
    NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
    private final int percentage;
    Code(int percentage) {
        this.percentage = percentage;
    }
}

@Data
class Shop_02 {

    Random random = new Random();

    private String name;

    Shop_02 (String mame) {
        this.name = name;
    }

    String getPrice(String product) {
        double price = calculatePrice(product);
        Code code = Code.values()[
                random.nextInt( Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    void delay() {

    }
}

class Quote {

    private final String shopName;
    private final double price;
    private final Code discountCode;
    public Quote(String shopName, double price, Code code) {
        this.shopName = shopName;
        this.price = price;
        this.discountCode = code;
    }

    public static Quote parse(String s) {
        String[] split = s.split(":");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        Code discountCode = Code.valueOf(split[2]); return new Quote(shopName, price, discountCode);
    }

    public String getShopName() { return shopName; }
    public double getPrice() { return price; }
    public Code getDiscountCode() { return discountCode; }
}


class Discount {

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        // delay();
        return 0;
    }
}