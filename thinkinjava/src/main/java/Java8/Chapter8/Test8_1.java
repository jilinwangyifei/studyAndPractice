package Java8.Chapter8;

import Java8.Chapter5.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test8_1 {

    ArrayList<Dish> menu = new ArrayList<>();

    //8.1.1改善代码的可读性
    void test8_1_1() {

    }

    //8.1.2 从匿名类到表达式的转变
    void test8_1_2() {
        Runnable runnable = () -> System.out.println("");

        //lambda中 this表示的是包含类
        int a = 10;
        Runnable r1 = () -> {
            //int a = 2; //会报错
            System.out.println(a);
        };

        //可以用显示的类型转换来解决模棱两可的情况
        /**
         *     interface Task{
         *         public void execute();
         *     }
         *     public static void doSomething(Runnable r){ r.run(); }
         *     public static void doSomething(Task a){ a.execute(); }
         *
         *     Task做显示转换
         *     doSomething((Task)() -> System.out.println("Danger danger!!"));
         */
    }

    //8.1.3 从lamdba表达式到方法引用的转换
    void test8_1_3() {

        //尽量考虑静态辅助方法
        int totalCalories = menu.stream().map(Dish::getCalories).reduce(0, (a,b) -> a+b);

        int totalCalories2 = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
    }

    //8.1.4 从命令式的数据处理到stream
    void test8_1_4() {
        //两种处理方式等价
        List<String> dishNames = new ArrayList<>();
        for(Dish dish: menu){
            if(dish.getCalories() > 300){
                dishNames.add(dish.getName());
            }
        }

        menu.stream().filter(d -> d.getCalories() > 300).map(Dish::getName).collect(Collectors.toList());
    }

    //8.1.5 增加代码的灵活性
    void test8_1_5() {
        //1.有条件的延迟

        //2.环绕执行
        //BufferedReaderProcessor 例子
    }

}



