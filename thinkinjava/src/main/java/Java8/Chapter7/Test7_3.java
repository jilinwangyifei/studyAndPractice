package Java8.Chapter7;

import org.junit.Test;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Test7_3 {

    //7.3.1拆分过程
    @Test
    public void test(){

        String SENTENCE = " Nel   mezzo del cammin  di nostra  vita " +
                "mi  ritrovai in una  selva oscura" +
                " ché la  dritta via era   smarrita ";

        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        System.out.println("Found " + new WordCounter().countWords(stream) + " words");

        //此方法会发生读取不正确的情况
        Stream<Character> stream2 = IntStream.range(0, SENTENCE.length()) .mapToObj(SENTENCE::charAt);
        System.out.println("Found " + new WordCounter().countWords(stream2) + " words");
    }

}
