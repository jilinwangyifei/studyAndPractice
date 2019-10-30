package Java8.Chapter2;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

public class Filter {

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> list1 = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)){
                list1.add(t);
            }
        }
        return list1;
    }

    public static void main(String[] args) {
        List<Apple> list1 = new ArrayList<>();

        //行为参数化例
        List<Apple> apples = Filter.filter(list1, (Apple apple) -> "red".equals(apple.getColor()));

        //用Comparator来排序
        list1.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

        //用Runnable执行代码
        Thread t  = new Thread(() -> System.out.println());
    }
}

@Data
class Apple{
    String color;
    Integer weight;
}