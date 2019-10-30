package Java8.Chapter5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Test5_3 {

    ArrayList<Dish> menu = new ArrayList<>();

    //allMatch   anyMatch   noneMatch   findFirst  findAny
    void test() {
        boolean isHealthy = menu.stream()
                .allMatch(d -> d.getCalories() < 1000);

        Optional<Dish> dish =
                menu.stream()
                        .filter(Dish::isVegetarian)
                        .findAny();

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream().
                map(i -> i * i).filter(i -> i % 3 == 0).findFirst();
    }



}
