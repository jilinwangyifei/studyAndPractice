package Java8.Chapter6;

import java.util.*;

import static java.util.stream.Collectors.*;
import Java8.Chapter5.Dish;
import Java8.Chapter5.Dish.CaloricLevel;

public class Test6_3 {

    ArrayList<Dish> menu = new ArrayList<>();

    //6.3.1多级分组
    void test6_3_1() {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                menu.stream().collect(groupingBy(Dish::getType,groupingBy(
                        dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })
                ));
    }


    //6.3.2 按子组收集数据
    void test6_3_2() {
        //查找菜单中热量最高的收收集器，按照菜的类型分类:
        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                menu.stream().collect(
                        groupingBy(Dish::getType,maxBy(Comparator.comparing(Dish::getCalories))));


        //1.把收集器的结果转换为另一个类型
        Map<Dish.Type, Dish> mostCaloricByType2 =
                menu.stream().collect(groupingBy(
                        Dish::getType,
                        collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)),
                        Optional::get)));

        //2. 与groupingBy联合使用的其他收集器的例子
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
                menu.stream().collect(groupingBy(Dish::getType, mapping(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;},toSet()
                )));

        //2. 与groupingBy联合使用的其他收集器的例子 toCollection
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType2 =
                menu.stream().collect(groupingBy(Dish::getType, mapping(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;},toCollection(HashSet::new)
                )));
    }
}
