package Java8.Chapter3;

import lombok.Data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import static java.util.Comparator.comparing;

public class Lambda {

    //函数式接口定义 只有一个方法的接口


    //3.6.2 构造函数引用
    void test3_6_2(){
        Supplier<Apple> supplier = Apple::new;
        Apple a1 = supplier.get();
        //等价于
        Supplier<Apple> supplier2 = () -> new Apple();


        Function<Integer,Orange> function = (weigth) -> new Orange(weigth);
        Orange o = function.apply(123);
        //等价于
        Function<Integer,Orange> function2 = Orange::new;
        Orange o2 = function2.apply(123);

    }

    //3.7 lambda和方法引用实践
    void test3_7(){
        ArrayList<Apple> list = new ArrayList<>();

        //1使用匿名类
        list.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        //2 使用lambda表达式
        list.sort((Apple o1, Apple o2) -> o1.getWeight().compareTo(o2.getWeight()));

        //3 使用lambda表达式再次简化
        list.sort((o1, o2) -> o1.getWeight().compareTo(o2.getWeight()));

        //4 运用comparing在简化
        Comparator<Apple> c = Comparator.comparing((Apple a) -> a.getWeight());

        //简化终极版 使用方法引用
        list.sort(Comparator.comparing(Apple::getWeight));
    }

    //3.8 复合Lambda表达式的有用方法
    void test3_8(){
        //按照质量的递减排序
        ArrayList<Apple> list = new ArrayList<>();
        list.sort(Comparator.comparing(Apple::getWeight).reversed());

        //比较器链  按照质量的递减排序 之后按照颜色排序
        list.sort(Comparator.comparing(Apple::getWeight).thenComparing(Apple::getColor));

        //谓词复合
        Predicate<Apple> redApple = (a) -> "red".equals(a.getWeight());
        Predicate<Apple> applePredicate = redApple.negate();

        //要么是150g已上的红苹果  要么是绿苹果
        Predicate<Apple> redAndHeavyAppleOrGreen = redApple.and(a -> a.getWeight()>150)
                .or(a->"green".equals(a.getColor()));


    }
}

class Lamba3_3{

    public static String processFile(BufferedReaderProcessor b) throws IOException {

        try (BufferedReader br =
                     new BufferedReader(new FileReader("data.txt"))) {
            return b.process(br);
        }

    }

    public static void main(String[] args) throws IOException{

        String oneLine = processFile((BufferedReader bufferedReader) -> bufferedReader.readLine());

        String twoLine = processFile((BufferedReader bufferedReader) ->
                bufferedReader.readLine()+bufferedReader.readLine());

        List<Integer> list1 = new ArrayList<>();
        //list1.sort(Comparator.comparing(Apple::getWeight));

    }

}


@Data
class Apple{
    String color;
    Integer weight;
}


@Data
class Orange{
    Orange(Integer weight){
        this.weight = weight;
    }

    String color;
    Integer weight;
}


