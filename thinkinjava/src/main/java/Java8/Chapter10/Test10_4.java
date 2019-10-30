package Java8.Chapter10;

import java.util.*;

public class Test10_4 {

    //10.4.1 使用Optional对null的封装
    void test10_4_1() {
        Map map = new HashMap();

        Optional<Object> value = Optional.ofNullable(map.get("key"));
    }

    //10.4.2 异常与Optional的对比
    void test10_4_2() {
        //将string 转成 integer 的工具类
        OptionalUtility.stringToInt("s");
    }


    //10.4.3 将所有内容整合起来
    void test10_4_3 () {

    }

    public int ReadDuration(Properties properties, String name) {
       return Optional.ofNullable(properties.getProperty(name)).
                flatMap(OptionalUtility::stringToInt).
                filter(i -> i > 0).
                orElse(0);
    }
}

class OptionalUtility{

    public static Optional<Integer> stringToInt (String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}