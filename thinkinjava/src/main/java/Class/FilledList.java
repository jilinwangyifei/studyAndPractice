package Class;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangbo on 2018/9/2.
 */
public class FilledList<T> {
    private Class<T> type;

    public FilledList(Class<T> type) {
        this.type = type;
    }

    public List<T> create(int elements) throws InstantiationException,IllegalAccessException {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < elements ; i++) {
            list.add(type.newInstance());
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
        FilledList<CountedInteger> filledList =
                new FilledList<CountedInteger>(CountedInteger.class);
        System.out.println(filledList.create(15));
    }
}

class CountedInteger {
    private static long counter;
    private final long id = counter++;
    public String toString() {
        return  Long.toString(id);
    }
}