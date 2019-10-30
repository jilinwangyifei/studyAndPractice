package Java8.Chapter8;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test8_4 {

    //8.4.2 使用日志调试
    @Test
    public void test8_4_2() {
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5);
        List<Integer> result = numbers.
                stream().
                peek(x -> System.out.println("from stream "+x)).
                map(x -> x + 17).
                peek(x -> System.out.println("after map "+x)).
                filter(x -> x % 2 == 0).
                peek(x -> System.out.println("after filter "+x)).
                limit(3).
                peek(x -> System.out.println("after limit "+x)).
                collect(Collectors.toList());
        System.out.println(result);
    }
}
