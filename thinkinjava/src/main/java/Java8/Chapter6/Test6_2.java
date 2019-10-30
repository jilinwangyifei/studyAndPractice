package Java8.Chapter6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import static java.util.stream.Collectors.*;
import Java8.Chapter5.Dish;

public class Test6_2 {

    ArrayList<Dish> menu = new ArrayList<>();

    //6.2.1 查找流中的最大值和最小值
    void test6_2_1(){
        Comparator<Dish> dishCaloriesComparator =
                Comparator.comparingInt(Dish::getCalories);

        Optional<Dish> mostCalorieDish =
                menu.stream()
                        .collect(maxBy(dishCaloriesComparator));

    }

    //6.2.2 汇总
    void test6_2_2(){
        //总和
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));

        //平均数
        double avgCalories = menu.stream().collect(averagingDouble(Dish::getCalories));

        //summarizingInt工厂方法  同时还有 long double 的SummaryStatistics
        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));

    }

    //6.2.3 连接字符串
    void test6_2_3(){
        String shortMenu = menu.stream().map(Dish::getName).collect(joining());

        //带分隔符
        String shortMenu2 = menu.stream().map(Dish::getName).collect(joining(", "));

    }


    //6.2.4 广义的规约汇总
    void test6_2_4(){
        //找到􏲰量最高的􏲫菜
        Optional<Dish> mostCalorieDish = menu.stream().
                collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories()? d1:d2));

        //1. 收集框架的灵活性 :以不同的方法执行同样的操作
        int totalCalories = menu.stream().collect(reducing(0, Dish::getCalories,Integer::sum));

        int totalCalories2 = menu.stream().mapToInt(Dish::getCalories).sum();

    }

}
