package Java8.Chapter5;

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

    public Dish() {}

    public enum Type { MEAT, FISH, OTHER }

    public enum CaloricLevel { DIET, NORMAL, FAT }
}
