package Java8.Chapter4;

import lombok.Data;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Data
public class Dish {

    private  String name;
    private  boolean vegetarian;
    private  int calories;
    private  Type type;


    public Dish() {
    }

    public enum Type { MEAT, FISH, OTHER }

    //4.2流简介
    void test4_2() {
        ArrayList<Dish> list = new ArrayList<>();
        List<String> threeHighCaloricDishNames =
                        list.stream().filter(dish ->
                                dish.getCalories() > 300).
                                        map(Dish::getName).limit(3).collect(toList());
    }


    //4.3.1只能遍历一次
    @Test
    public void test4_3_1() {
        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
        s.forEach(System.out::println);
    }

    //4.3.2外部迭代与内部迭代
    @Test
    public void test4_3_2() {
    }

    //4.4流操作
    @Test
    public void test4_4() {
        //中间操作filter map limit 和 终端操作collect
    }
}
