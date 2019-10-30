package Java8.Chapter7;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Test7_1 {

    //7.1.1将顺序流转为并行流
    void test7_1_1() {

        Stream.iterate(1l ,i -> i+1).
                limit(10).parallel().reduce(0l,Long::sum);

        //sequential 方法转变为顺序流

    }

    //7.1.2 测量流性能
    void test7_1_2(int n ) {

        //使用并行执行并不快的写法 iterate依赖上次生成的结果导致速度并不快
        Stream.iterate(1l ,i -> i+1).
                limit(n).parallel().reduce(0l,Long::sum);

        //正确写法
        LongStream.rangeClosed(1, n).parallel().reduce(0l, Long::sum);

    }


    //7.1.3 正确使用并行流
    void test7_1_3(int n ) {
        //错误写法 问题在于forEach中调用的方法有副作用
        sideEffectSum(n);
    }
    public long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1,n).forEach(accumulator::add);
        return accumulator.total;
    }

    class Accumulator {
        long total = 0;
        void add(long value) {
            total += value;
        }
    }

    //7.1.4 高效使用并行流
    void test7_1_4(int n ) {
        //错误写法 问题在于forEach中调用的方法有副作用
        sideEffectSum(n);
    }

}
