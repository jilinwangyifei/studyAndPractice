package Java8.Chapter5;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Test5_4 {

    List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);

    //5.4.1元素求和
    void test5_4_1() {
        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        //====>>>
        int product3 = numbers.stream().reduce(1, Integer::sum);

        //无初始值
        Optional<Integer> product2 =  numbers.stream().reduce(Integer::sum);

    }

    //5.4.2求最大值和最小值
    void test5_4_2() {
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
    }

}
