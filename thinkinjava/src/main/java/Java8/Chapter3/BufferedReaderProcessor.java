package Java8.Chapter3;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BufferedReaderProcessor {

    String process (BufferedReader reader) throws IOException;
}
