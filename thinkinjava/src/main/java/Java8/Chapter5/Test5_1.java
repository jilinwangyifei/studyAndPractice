package Java8.Chapter5;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test5_1 {

    ArrayList<Dish> menu = new ArrayList<>();

    //5.1.1用谓词筛选
    void test5_1_1(){
        List<Dish> list = menu.stream().
                filter(Dish::isVegetarian).collect(Collectors.toList());
    }


    //5.1.2筛选各异的元素
    @Test
    public void test5_1_2(){
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().
                filter(i -> i%2 == 0).distinct().forEach(System.out::println);
    }


    //5.1.3截短流
    public void test5_1_3(){
        List<Dish> list = menu.stream().
                filter(d -> d.getCalories() > 300).
                    limit(3).collect(Collectors.toList());
    }

    //5.1.4跳过元素
    public void test5_1_4(){
        List<Dish> list = menu.stream().
                filter(d -> d.getCalories() > 300).
                    skip(3).collect(Collectors.toList());
    }

}
