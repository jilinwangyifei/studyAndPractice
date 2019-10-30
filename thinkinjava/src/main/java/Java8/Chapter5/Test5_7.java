package Java8.Chapter5;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test5_7 {

    //5.7.1创建流
    void test5_7_1() {
        Stream <String> stream = Stream.of("hello","world");
        stream.map(String::toUpperCase).forEach(System.out::println);
    }

    //5.7.2由数组创建流
    void test5_7_2() {
        int[] numbers = {2, 3, 5, 7, 11, 13};
        IntStream intStream = Arrays.stream(numbers);
    }

    //5.7.3 由文件创建流
    void test5_7_3() {
        long uniqueWords = 0;
        try(Stream<String> lines =
            Files.lines(Paths.get("data.txt"), Charset.defaultCharset())){
                uniqueWords = lines.flatMap(line ->
                        Arrays.stream(line.split(" "))).distinct().count();
        }
        catch(IOException e){
        }
    }

    //5.7.4 由函数生成流
    void test5_7_4() {

        //1 迭代
        Stream.iterate(0, n -> n + 2).limit(2).forEach(System.out::println);

        //斐波那契数列
        Stream.iterate(new int[]{0,1},t -> new int[]{t[1],t[1]+t[0]}).limit(10).
                map(t -> t[0]).forEach(System.out::println);

        //2 生成
        Stream.generate(Math::random).limit(5).forEach(System.out::println);

    }

}
