package Java8.Chapter5;

import java.util.ArrayList;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test5_6 {

    ArrayList<Dish> menu = new ArrayList<>();

    //5.6.1原始类型􏱻特化
    void test5_6_1() {
        //1. 􏲎􏲏映射到到数值􏱻流
        int colors = menu.stream().mapToInt(d -> d.getCalories()).sum();

        //2.转换􏲝􏲞回对象􏱻
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();

        //3. 默认值OptionalInt
        OptionalInt optionalInt = menu.stream().mapToInt(Dish::getCalories).max();

        int max = optionalInt.orElse(1);
    }

    //5.6.2数值范围
    void test5_6_2() {
        IntStream intStream = IntStream.range(1,100).filter(n -> n % 2 == 0);
    }

    //5.6.3数值流应用 勾股数
    void test5_6_3() {
        Stream<double[]> pythagoreanTriples = IntStream.rangeClosed(1,100).boxed().
                flatMap(a -> IntStream.rangeClosed(a, 100).
                        mapToObj(b -> new double[]{a,b,Math.sqrt(a*a+b*b)}).
                        filter(t -> t[2] % 1 ==0));
    //标注 flatMap又是怎么回事呢?首先，创建一个从1到100的数值􏰂围来生成a的值。
        // 对每 个给定的a值，创建一个三元数流。要是把a的值映射到三元数流的话，
        // 就会得到一个由流构成的 流。flatMap方法在做映射的同时，
        // 还会把所有生成的三元数流􏰫􏰦化成一个流。这样你就得到 了一个三元数流。
    }
}
