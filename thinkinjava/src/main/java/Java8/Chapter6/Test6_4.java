package Java8.Chapter6;

import Java8.Chapter5.Dish;
import java.util.*;
import static java.util.stream.Collectors.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Test6_4 {
    ArrayList<Dish> menu = new ArrayList<>();

    //6.4.1分区的优势
    void test6_4_1() {
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType =
                menu.stream().collect(
                        partitioningBy(Dish::isVegetarian,groupingBy(Dish::getType)));

        //素食和非素食中的热量最高的菜
        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
                menu.stream().collect(
                        partitioningBy(Dish::isVegetarian,
                                        collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)),
                                        Optional::get)));

    }

    //6.4.2 将数􏲺按􏵉质数和非质数分区
    @Test
    public void test6_4_2() {
        System.out.println(partitionPrimes(6));
    }


    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.range(2, n).boxed().collect(partitioningBy(dd -> isPrime(dd)));
    }


    public boolean isPrime(int candidate) {
        return IntStream.range(2, candidate).
                noneMatch(i -> candidate % i == 0);
    }
}
