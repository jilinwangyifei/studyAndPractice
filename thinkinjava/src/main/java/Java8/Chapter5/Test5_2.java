package Java8.Chapter5;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test5_2 {

    ArrayList<Dish> menu = new ArrayList<>();

    //5.2.1对􏱻流中􏲐一个􏱳􏱴应用函数
    void test5_2_1() {
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream().map(String::length).collect(Collectors.toList());
    }



    //5.2.2流的扁平化  flatmap中的方法把一个流中的每个值都换成另一个流 然后把所有的流连成一个流
    @Test
    public void test5_2_2() {
        List<String> words = Arrays.asList("Goodbye", "World");
        List<String> list = words.stream().map(s -> s.split("")).
                flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(list);
    }


    //课后练习题
    void  test() {
        //1题
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream().map(s-> s*s).collect(Collectors.toList());

        //2题
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<Integer[]> arrays =
                numbers1.stream().
                        flatMap(i -> numbers2.stream().
                                map(j -> new Integer[]{i,j})
                        ).
                        collect(Collectors.toList());


        //3题 只返回能被3整除的数组对
        List<Integer[]> arrays2 =
                numbers1.stream().
                        flatMap(i -> numbers2.stream().
                                filter(j -> (i+j)%3 == 0).
                                map(j -> new Integer[]{i,j})
                        ).
                        collect(Collectors.toList());
    }


}
